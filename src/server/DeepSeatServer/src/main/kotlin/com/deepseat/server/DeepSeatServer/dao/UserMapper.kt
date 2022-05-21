package com.deepseat.server.DeepSeatServer.dao

import com.deepseat.server.DeepSeatServer.vo.User
import org.springframework.stereotype.Repository

@Repository
interface UserMapper {

    fun insertUser(user: User)
    fun getUser(userID: String): User?
    fun getUserByNickname(nickname: String): User?
    fun deleteUser(userID: String)
    fun updateUser(user: User)
    fun verifyUser(userID: String)

}
