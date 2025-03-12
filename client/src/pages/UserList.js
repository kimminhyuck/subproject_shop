import React, {useEffect, useState} from 'react'
import {getUsers, addUser} from '../api/auth/userService'

const UserList = () => {
  const [users, setUsers] = useState([])
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')

  useEffect(() => {
    getUsers()
      .then(data => setUsers(data))
      .catch(err => console.error(err))
  }, [])

  const handleAddUser = async () => {
    const newUser = {name, email}
    const addedUser = await addUser(newUser)
    setUsers([...users, addedUser])
    setName('')
    setEmail('')
  }

  return (
    <div>
      <h2>사용자 목록</h2>
      <ul>
        {users.map(user => (
          <li key={user.id}>
            {user.name} - {user.email}
          </li>
        ))}
      </ul>
      <div>
        <input
          type="text"
          placeholder="이름"
          value={name}
          onChange={e => setName(e.target.value)}
        />
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />
        <button onClick={handleAddUser}>사용자 추가</button>
      </div>
    </div>
  )
}

export default UserList
