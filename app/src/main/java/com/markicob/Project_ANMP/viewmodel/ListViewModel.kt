package com.markicob.Project_ANMP.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markicob.Project_ANMP.model.Habit

class ListViewModel: ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    fun refresh() {
        loadingLD.value = true 			// progress bar start muncul
        habitLoadErrorLD.value = false  	// tidak ada error

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



}