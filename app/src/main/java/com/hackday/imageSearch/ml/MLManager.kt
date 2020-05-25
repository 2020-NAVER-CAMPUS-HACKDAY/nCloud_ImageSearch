package com.hackday.imageSearch.ml

import android.content.Context
import android.content.SharedPreferences

class MLManager(context: Context) {

    val fileName = "urlCheck"
    val key = ""
    val prefs: SharedPreferences = context.getSharedPreferences(fileName, 0)

    var lastImageAddedDate: String?
        get() = prefs.getString(key, null)
        set(value) = prefs.edit().putString(key, value).apply()

}