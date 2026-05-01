package com.markicob.Project_ANMP.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.markicob.Project_ANMP.model.Habit

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false

        habitsLD.value = arrayListOf(
            Habit(
                id = "1",
                habitName = "Drink Water",
                description = "Stay hydrated throughout the day",
                progress = 3,
                goal = 8,
                unit = "glasses",
                icon = "water"
            ),
            Habit(
                id = "2",
                habitName = "Exercise",
                description = "Daily workout routine",
                progress = 15,
                goal = 30,
                unit = "minutes",
                icon = "exercise"
            ),
            Habit(
                id = "3",
                habitName = "Read Books",
                description = "Expand your knowledge",
                progress = 20,
                goal = 20,
                unit = "pages",
                icon = "book"
            ),
            Habit(
                id = "4",
                habitName = "Walk",
                description = "Walk at least 10.000 steps a day",
                progress = 0,
                goal = 10000,
                unit = "steps",
                icon = "walk"
            )
        )

        habitLoadErrorLD.value = false
        loadingLD.value = false
    }
    fun addHabit(habit: Habit) {
        val currentList = habitsLD.value ?: arrayListOf()
        currentList.add(habit)
        //repository.saveHabits(currentList)
        habitsLD.value = currentList
    }

    fun updateProgress(position: Int, delta: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList[position]
        val newProgress = ((habit.progress ?: 0) + delta).coerceAtLeast(0)
        habit.progress = newProgress
       // repository.saveHabits(currentList)
        habitsLD.value = currentList
    }
}