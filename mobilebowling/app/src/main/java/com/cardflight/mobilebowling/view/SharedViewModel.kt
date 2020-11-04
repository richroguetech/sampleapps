package com.cardflight.mobilebowling.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val roll1 = MutableLiveData<String>()
    val roll2 = MutableLiveData<String>()
    val roll3 = MutableLiveData<String>()

    fun sendRoll1Score(text: String) {
        roll1.value = text
    }

    fun sendRoll2Score(text: String) {
        roll2.value = text
    }

    fun sendRoll3Score(text: String) {
        roll3.value = text
    }
}