package com.example.common.utils.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class TokenStorageHelper @Inject constructor(private val encryptedPreference: SharedPreferences) {
    fun getToken(defaultValue: String) = encryptedPreference.getString(TOKEN, defaultValue)
    fun setToken(token: String) = encryptedPreference.edit().putString(TOKEN, token).apply()

    companion object {
        const val TOKEN = "token"
    }
}