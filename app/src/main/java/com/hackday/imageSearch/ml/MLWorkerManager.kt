package com.hackday.imageSearch.ml

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class MLWorkerManager (context: Context) {

    val fileName = "uidCheck"
    val key = ""
    val prefs: SharedPreferences = context.getSharedPreferences(fileName, 0)

    var lastUID: String?
        get() = prefs.getString(key, null)
        set(value) = prefs.edit().putString(key, value).apply()
}