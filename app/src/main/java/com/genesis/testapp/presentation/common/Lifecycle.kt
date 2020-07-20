package com.genesis.testapp.presentation.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlin.reflect.KFunction1

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: KFunction1<T, Unit>) =
    liveData.observe(this, Observer(body))
