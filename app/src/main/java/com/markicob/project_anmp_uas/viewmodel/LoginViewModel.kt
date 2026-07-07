package com.markicob.project_anmp_uas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.markicob.project_anmp_uas.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val loginResultLD = MutableLiveData<Boolean>()
    val autoLoginLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun checkSession() {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            val user = db.userDao().getLoggedInUser()
            autoLoginLD.postValue(user != null)
        }
    }

    fun login(username: String, password: String) {
        launch {
            val db = HabitDatabase.buildDatabase(getApplication())
            val user = db.userDao().login(username, password)
            if (user != null) {
                user.isLoggedIn = 1
                db.userDao().updateUser(user)
                loginResultLD.postValue(true)
            } else {
                loginResultLD.postValue(false)
            }
        }
    }
}