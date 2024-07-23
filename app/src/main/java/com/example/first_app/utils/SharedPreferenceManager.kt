package com.example.first_app.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.first_app.composables.screens.LoginScreen


fun saveSessionId(context: Context, sessionId: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("session_id", sessionId)
    editor.apply()
}


fun getSessionId(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    return sharedPreferences.getString("session_id", null)
}


fun verifySessionId(context: Context) {
    val sessionId = getSessionId(context)
    if (sessionId != null) {
        Log.d("SessionVerification", "Session ID is set: $sessionId")
    } else {
        Log.d("SessionVerification", "Session ID is not set")
    }
}


fun removeSessionId(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    return sharedPreferences.edit().remove("session_id").apply()
}