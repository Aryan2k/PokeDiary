package com.aryanm468.pokediary.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    fun backgroundCall(call: suspend () -> Unit): Job {
        return viewModelScope.launch {
            withContext(Dispatchers.IO) {
                call()
            }
        }
    }

}