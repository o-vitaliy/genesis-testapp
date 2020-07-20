package com.genesis.testapp.presentation.common

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val v = view ?: return
    Snackbar.make(v, message, length).show()
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}
