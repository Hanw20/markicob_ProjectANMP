package com.markicob.Project_ANMP.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Habit(

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var habitName:String?,
    var description:String?,
    var progress: Int?,
    var goal: Int?,
    var unit:String?,
    var icon:String?
)