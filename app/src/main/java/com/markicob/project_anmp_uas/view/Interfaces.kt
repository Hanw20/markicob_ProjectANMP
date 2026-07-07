package com.markicob.project_anmp_uas.view

import com.markicob.project_anmp_uas.model.Habit

interface HabitCardListener {
    fun onAddClick(habit: Habit)
    fun onMinClick(habit: Habit)
    fun onTitleClick(habit: Habit)
}

interface EditHabitListener {
    fun onSubmitClick()
}