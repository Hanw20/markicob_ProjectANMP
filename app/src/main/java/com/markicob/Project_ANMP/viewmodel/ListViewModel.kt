package com.markicob.Project_ANMP.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        val savedHabits = fileHelper.readFromFile()
        if (savedHabits.isEmpty()) {
            val dummy = arrayListOf(
                Habit("1", "Drink Water", "Stay hydrated", 3, 8, "glasses", "Water"),
                Habit("2", "Exercise", "Daily workout", 15, 30, "minutes", "Exercise")
            )
            habitsLD.value = dummy
            val jsonString = Gson().toJson(dummy)
            fileHelper.writeToFile(jsonString)

        } else {
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            val habitsFromFile: ArrayList<Habit> = Gson().fromJson(savedHabits, type)
            habitsLD.value = habitsFromFile
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
        val jsonString = Gson().toJson(currentList)
        fileHelper.writeToFile(jsonString)
    }

    fun updateProgress(position: Int, delta: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList[position]
        val newProgress = ((habit.progress ?: 0) + delta).coerceAtLeast(0)
        habit.progress = newProgress
       // repository.saveHabits(currentList)
        habitsLD.value = currentList
        val jsonString = Gson().toJson(currentList)
        fileHelper.writeToFile(jsonString)
    }
}