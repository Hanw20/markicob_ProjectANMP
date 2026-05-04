package com.markicob.Project_ANMP.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.markicob.Project_ANMP.model.Habit
import com.markicob.Project_ANMP.util.FileHelper

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val fileHelper = FileHelper(getApplication())
    private var allHabits = arrayListOf<Habit>()
    fun refresh() {
        loadingLD.value = true
        val savedHabits = fileHelper.loadHabits(getApplication())
        if (savedHabits.isEmpty()) {
            val dummy = arrayListOf(
                Habit("1", "Drink Water", "Stay hydrated", 3, 8, "glasses", "Water"),
                Habit("2", "Exercise", "Daily workout", 15, 30, "minutes", "Exercise")
            )
            habitsLD.value = dummy
            fileHelper.saveHabits(getApplication(), dummy)
        } else{
            habitsLD.value = savedHabits
        }
       //habitsLD.value = allHabits
       // habitLoadErrorLD.value = false
        loadingLD.value = false
        habitLoadErrorLD.value = false
    }
    fun addHabit(habit: Habit) {
        //val currentList = habitsLD.value ?: arrayListOf()
        //currentList.add(habit)
        //allHabits.add(habit)
        //repository.saveHabits(currentList)
        //habitsLD.value = allHabits
        val currentList = habitsLD.value ?: arrayListOf()
        currentList.add(habit)
        habitsLD.value = currentList
        fileHelper.saveHabits(getApplication(), currentList)
    }

    fun updateProgress(position: Int, delta: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList[position]
        val newProgress = ((habit.progress ?: 0) + delta).coerceAtLeast(0)
        habit.progress = newProgress
       // repository.saveHabits(currentList)
        habitsLD.value = currentList
        fileHelper.saveHabits(getApplication(),currentList)
    }
}