package com.orinab.shoporinab.data.repository

import com.orinab.shoporinab.data.model.room_db.User
import com.orinab.shoporinab.data.room.UserDatabase


class UserRepository(private val userDatabase: UserDatabase) {

    suspend fun getUser() = userDatabase.userDAO().getUser()
    suspend fun insertUser(user: User) = userDatabase.userDAO().insertUser(user)
    suspend fun updateUser(user: User) = userDatabase.userDAO().updateUser(user)
    suspend fun getUsers() = userDatabase.userDAO().getUsers()
    suspend fun delete() = userDatabase.userDAO().delete()

}