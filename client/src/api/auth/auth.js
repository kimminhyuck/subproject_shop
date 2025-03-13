import api from '../axios' // axios.js에서 공통 설정을 가져옴

const requestConfig = {
  withCredentials: true,
  headers: {
    'Cache-Control': 'no-store',
    'Content-Type': 'application/json'
  }
}

// let isRefreshing = false; // 리프레시 토큰 요청 상태 관리

const handleRequest = async (requestPromise, errorMessage) => {
  try {
    const response = await requestPromise
    return response.data
  } catch (error) {
    const originalRequest = error.config
    if (error.response?.status === 401 && !originalRequest._retry) {
      console.log('401 Unauthorized 발생: 리프레시 토큰 요청 시도')
      if (!originalRequest._retry) {
        originalRequest._retry = true
        try {
          await authAPI.refreshToken() // 리프레시 토큰 요청
          return api(originalRequest) // 원래 요청 다시 시도
        } catch (refreshError) {
          console.error('리프레시 토큰 만료, 로그인 필요')
          authAPI.logoutUser() // 로그아웃 처리
          throw refreshError
        }
      }
    }
    throw error.response?.data || new Error(errorMessage)
  }
}

// 브라우저 쿠키 수동 삭제
const clearCookiesManually = () => {
  document.cookie = 'accessToken=; Max-Age=0; path=/;'
  document.cookie = 'refreshToken=; Max-Age=0; path=/;'
}

export const authAPI = {
  registerUser: userData =>
    handleRequest(
      api.post('/auth/register', userData, requestConfig),
      '회원가입 요청 중 오류 발생'
    ),

  loginUser: async userData => {
    try {
      const response = await api.post('/auth/login', userData, {withCredentials: true})

      return response
    } catch (error) {
      throw error
    }
  },

  logoutUser: async () => {
    await handleRequest(
      api.post('/auth/logout', {}, requestConfig),
      '로그아웃 요청 중 오류 발생'
    )
    clearCookiesManually()
  },

  getUserProfile: async () => {
    try {
      const response = await api.get('/auth/profile', requestConfig)
      return response.data
    } catch (error) {
      if (error.response?.status === 400) {
        return null //  400 에러가 발생해도 콘솔에 표시되지 않도록 무시
      }

      if (error.response?.status === 401) {
        console.warn('인증 실패: 자동 로그아웃 처리')
        await authAPI.logoutUser()
        return null
      }

      console.error('프로필 조회 실패:', error.response?.data || error)
      return null
    }
  },

  checkDuplicate: data => {
    if (!data || Object.values(data).every(val => !val.trim())) {
      return Promise.reject({message: '입력된 값이 없습니다.'})
    }
    return handleRequest(
      api.post('/auth/check-duplicate', data, requestConfig),
      '중복 확인 중 오류 발생'
    )
  },

  updateProfile: userData =>
    handleRequest(
      api.put('/auth/profile/update', userData, requestConfig),
      '프로필 수정 중 오류 발생'
    ),

  changePassword: passwordData =>
    handleRequest(
      api.put('/auth/change-password', passwordData, requestConfig),
      '비밀번호 변경 중 오류 발생'
    ),

  //  비밀번호 찾기 요청에서만 withCredentials 제거
  forgotPassword: email =>
    handleRequest(
      api.post(
        '/auth/forgot-password',
        {email},
        {
          headers: {
            'Cache-Control': 'no-store',
            'Content-Type': 'application/json'
          }
        }
      ),
      '비밀번호 찾기 요청 중 오류 발생'
    ),

  resetPassword: resetData =>
    handleRequest(
      api.post('/auth/reset-password', resetData, requestConfig),
      '비밀번호 변경 중 오류 발생'
    ),

  refreshToken: async () => {
    console.log(' [클라이언트] 리프레시 토큰 요청 실행됨')

    try {
      const response = await api.post(
        '/auth/refresh-token',
        {},
        {
          withCredentials: true
        }
      )

      console.log(' [클라이언트] 새 액세스 토큰 수신:', response.data)
      return response.data
    } catch (error) {
      console.error(
        ' [클라이언트] 리프레시 토큰 요청 실패:',
        error.response?.data || error
      )
      throw error
    }
  },

  //  아이디 찾기 API 요청
  findUserId: async email => {
    // console.log(' 아이디 찾기 요청 시작:', email); //  요청 확인
    try {
      const response = await handleRequest(
        api.post('/auth/find-userid', {email}, requestConfig),
        '아이디 찾기 요청 중 오류 발생'
      )
      // console.log(' 아이디 찾기 API 응답:', response); //  서버 응답 확인
      return response
    } catch (error) {
      console.error(' 아이디 찾기 API 요청 실패:', error.response?.data || error) //  오류 로그 출력
      throw error
    }
  },

  //  인증 코드 검증 API 추가
  verifyCode: async ({email, code}) => {
    console.log(' [클라이언트] 인증 코드 검증 요청:', email, code) // 디버깅
    return handleRequest(
      api.post('/auth/verify-code', {email, code}, requestConfig),
      '인증 코드 확인 요청 중 오류 발생'
    )
  }
}

export default authAPI
