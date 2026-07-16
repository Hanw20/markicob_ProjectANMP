package com.markicob.project_anmp_uas.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import com.markicob.project_anmp_uas.util.util

@Database(
    arrayOf(Habit::class, User::class), version=1
)
abstract class HabitDatabase : RoomDatabase(){
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var instance: HabitDatabase?=null

        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java, "habitdb"
                ).build().also { instance = it }
            }
//            Room.databaseBuilder(
//                context.applicationContext,
//                HabitDatabase::class.java,
//                "habitdb"
//            ).build()

        operator fun invoke(context: Context) {
            if (instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
        // Fungsi baru: seed user hardcode kalau tabel masih kosong
        suspend fun seedUserIfNeeded(context: Context) {
            withContext(Dispatchers.IO) {
                val db = buildDatabase(context)
                if (db.userDao().selectAllUser().isEmpty()) {
                    db.userDao().insertAll(
                        User(username = "student", password = "123")
                    )
                }
            }
        }
    }
}