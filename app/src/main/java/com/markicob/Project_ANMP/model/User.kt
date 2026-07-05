package com.markicob.Project_ANMP.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username:String,
    var password:String,
    var isLoggedIn: Int = 0
)