package com.orinab.shoporinab.data.room

import androidx.room.*
import com.orinab.shoporinab.data.model.room_db.User

@Dao
interface UserDAO {

    @Query("select * from user_entity")
    suspend fun getUser(): User?

    @Query("select * from user_entity")
    fun getUserLocal(): User?

    @Query("select * from user_entity")
    suspend fun getUsers(): List<User>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user_entity")
    suspend fun delete()
}