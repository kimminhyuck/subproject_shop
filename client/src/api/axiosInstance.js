import axios from 'axios'

const api = axios.create({
  baseURL: '/api', // proxy 설정 덕분에 "/api"만 써도 자동으로 "http://localhost:8081/api"로 요청
  withCredentials: true
})

export default api
