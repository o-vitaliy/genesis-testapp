package com.genesis.testapp.data

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {
    fun getString(@StringRes id: Int) = context.getString(id)
    @SuppressWarnings("SpreadOperator")
    fun getString(@StringRes id: Int, vararg args: Any) = context.getString(id, *args)
}
