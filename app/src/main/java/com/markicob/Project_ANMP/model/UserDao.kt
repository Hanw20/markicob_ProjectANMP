package com.markicob.Project_ANMP.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (vararg user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    fun login(username:String, password: String): User

    @Query("SELECT * FROM user WHERE isLoggedIn = 1 LIMIT 1")
    fun getLoggedInUser(): User?
}