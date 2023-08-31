package com.example.shelf.constant

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


class AppPreferences(val context: Context) {

    private var sharedPrefs: SharedPreferences? = null
    private var prefsEditor: SharedPreferences.Editor? = null
    private var appContext: Context? = null

    init {
        this.sharedPrefs = context.getSharedPreferences(
            "com.example.shelf",
            Activity.MODE_PRIVATE
        )
        this.prefsEditor = sharedPrefs?.edit()
        this.prefsEditor?.commit()
        this.appContext = context
    }


    @Synchronized
    fun setUsername(username: String) {
        val editor = sharedPrefs!!.edit()
        editor!!.putString(USERNAME, username)

        editor.apply()
    }

    @Synchronized
    fun getUsername(): String {
        return sharedPrefs?.getString(USERNAME, "")!!
    }


    @Synchronized
    fun setPass(username: String) {
        val editor = sharedPrefs!!.edit()
        editor!!.putString(PASSWORD, username)

        editor.apply()
    }

    @Synchronized
    fun getPass(): String {
        return sharedPrefs?.getString(PASSWORD, "")!!
    }

    val USERNAME = "USERNAME"
    val PASSWORD = "PASSWORD"

}
