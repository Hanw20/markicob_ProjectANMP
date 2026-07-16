package com.markicob.project_anmp_uas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.markicob.project_anmp_uas.model.Habit
import com.markicob.project_anmp_uas.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val habitDetailLD = MutableLiveData<Habit>()

//    private val fileHelper = FileHelper(getApplication())
//    private var allHabits = arrayListOf<Habit>()
//
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.postValue(true)
        habitLoadErrorLD.postValue(false)
        launch{
            val db = HabitDatabase.buildDatabase(getApplication())
            habitsLD.postValue(ArrayList(db.habitDao().selectAllHabit()))
            loadingLD.postValue(false)
        }
    }
    fun addHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            db.habitDao().insertAll(habit)
            habitsLD.postValue(ArrayList(db.habitDao().selectAllHabit()))
        }
    }
    fun updateProgress(habit: Habit, delta: Int) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            habit.progress = (habit.progress + delta).coerceAtLeast(0)
            db.habitDao().updateHabit(habit)
            habitsLD.postValue(ArrayList(db.habitDao().selectAllHabit()))
        }
    }

    fun fetch(id: Int) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            habitDetailLD.postValue(db.habitDao().selectHabit(id))
        }
    }

    fun updateHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            db.habitDao().updateHabit(habit)
            habitsLD.postValue(ArrayList(db.habitDao().selectAllHabit()))
        }
    }

}