import axios from 'axios'

// 환경변수(REACT_APP_API_URL)가 설정되어 있으면 해당 값을 사용하고, 없으면 proxy 설정('/api')를 사용
const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL || '/api',
  timeout: 20000, // 서버 초기 로딩 시간 증가 시 타임아웃 방지
  withCredentials: true, // httpOnly 쿠키 전송 허용
  headers: {
    'Cache-Control': 'no-store',
    'Content-Type': 'application/json'
  }
})

let isRefreshing = false
let failedQueue = [] // 리프레시 토큰 요청 대기 큐

// 실패한 요청 큐 처리 함수
const processQueue = (error, token = null) => {
  failedQueue.forEach(prom => {
    if (error) {
      prom.reject(error)
    } else {
      prom.resolve(token)
    }
  })
  failedQueue = []
}

// 응답 인터셉터: 401 발생 시 리프레시 토큰 요청 후 원래 요청 재시도
api.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true

      if (isRefreshing) {
        return new Promise((resolve, reject) => {
          failedQueue.push({resolve, reject})
        })
          .then(token => {
            originalRequest.headers['Authorization'] = `Bearer ${token}`
            return api(originalRequest)
          })
          .catch(err => Promise.reject(err))
      }

      isRefreshing = true

      try {
        console.log('[Client] Refresh token request started')
        const res = await api.post('/auth/refresh-token')

        if (res.status === 200 && res.data.accessToken) {
          console.log('[Client] Refresh token successful:', res.data.accessToken)
          const newAccessToken = res.data.accessToken
          // 새 토큰을 기본 헤더에 저장
          api.defaults.headers.common['Authorization'] = `Bearer ${newAccessToken}`
          processQueue(null, newAccessToken)
          isRefreshing = false
          originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`
          return api(originalRequest)
        }
      } catch (refreshError) {
        console.error('[Client] Refresh token failed:', refreshError)
        processQueue(refreshError, null)
        isRefreshing = false
        window.location.href = '/login' // 로그인 페이지로 리디렉트
      }
    }

    return Promise.reject(error)
  }
)

// 로그아웃 함수 (필요 시 호출)
export const logout = async () => {
  try {
    const response = await api.post('/auth/logout', {})
    return response.data
  } catch (error) {
    console.error('Logout failed:', error)
  }
}

export default api
