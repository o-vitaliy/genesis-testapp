package com.genesis.testapp.presentation.common

import android.view.View

fun View.visible(value: Boolean, invisibility: Int = View.GONE) {
    visibility = if (value) View.VISIBLE else invisibility
}
