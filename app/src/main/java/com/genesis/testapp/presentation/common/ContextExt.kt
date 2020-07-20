package com.genesis.testapp.presentation.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(link: String?) {
    try {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    } catch (ex: ActivityNotFoundException) {
        toast("Unable to open: $link")
    }
}