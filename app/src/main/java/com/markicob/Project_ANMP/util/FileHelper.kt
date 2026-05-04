package com.markicob.Project_ANMP.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.markicob.Project_ANMP.model.Habit

class FileHelper (private val context: Context) {
    private const val fileName = "habits.json"

    fun saveHabits(context: Context, habits: ArrayList<Habit>) {
        val gson = Gson()
        val jsonString = gson.toJson(habits)
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(jsonString.toByteArray())
        }
    }

    fun loadHabits(context: Context): ArrayList<Habit> {
        val file = context.getFileStreamPath(fileName)
        if (!file.exists()) return arrayListOf()

        return try {
            val jsonString = context.openFileInput(fileName).bufferedReader().use { it.readText() }
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}