package com.genesis.testapp.data.prefs

import android.content.Context
import android.content.SharedPreferences

object PrefsSourceFactory {
    fun create(context: Context): SharedPreferences = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )
}
