package com.genesis.testapp.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.postValue(value: T) {
    (this as? MutableLiveData)?.postValue(value) ?: error("$this is not MutableLiveData")
}

fun <T> LiveData<T>.setValue(value: T) {
    (this as? MutableLiveData)?.setValue(value) ?: error("$this is not MutableLiveData")
}
