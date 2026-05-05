package com.markicob.Project_ANMP.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FileHelper(val context: Context) {
    val folderName = "myfolder"
    val fileName = "habits.txt"

    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if (!dir.exists()) {
            dir.mkdirs() // bikin folder jika folder belum ada
        }
        return File(dir, fileName)
    }
    fun writeToFile(data: String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use { output ->
                output.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun readFromFile(): String {
        return try {
            val file = getFile()
            file.bufferedReader().useLines { lines ->
                lines.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace().toString()
            "" // Kembalikan string kosong jika error
        }
    }
}