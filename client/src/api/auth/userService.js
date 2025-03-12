import api from '../axiosInstance'

export const getUsers = async () => {
  try {
    const response = await api.get('/users')
    return response.data
  } catch (error) {
    console.error('Error fetching users:', error)
    throw error
  }
}

export const addUser = async user => {
  try {
    const response = await api.post('/users', user)
    return response.data
  } catch (error) {
    console.error('Error adding user:', error)
    throw error
  }
}
