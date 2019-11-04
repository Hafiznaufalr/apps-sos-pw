package digitalusus.net.util

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


object Preferences {
    private val KEY_ID = "Username_logged_in"
    private val KEY_NAME = "Username_logged_in"
    private val KEY_EMAIL = "Username_logged_in"
    private val KEY_NIS = "Username_logged_in"
    private val KEY_AVATAR = "Username_logged_in"
    private val KEY_STATUS = "Status_logged_in"

    private fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setName(context: Context, name: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }

    fun getName(context: Context): String {
        return getSharedPreference(context).getString(KEY_NAME, "")!!
    }

    fun setStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(KEY_STATUS, status)
        editor.apply()
    }

    fun getStatus(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(KEY_STATUS, false)
    }

    fun clearUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_NAME)
        editor.remove(KEY_STATUS)
        editor.apply()
    }
}