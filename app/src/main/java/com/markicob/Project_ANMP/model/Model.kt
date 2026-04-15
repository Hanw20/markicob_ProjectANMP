package com.markicob.Project_ANMP.model
import com.google.gson.annotations.SerializedName

data class Habit(
    var id:String?,
    var habitName:String?,
    var description:String?,
    var progress: Int?,
    var goal: Int?,
    var unit:String?,
    var icon:String?
)