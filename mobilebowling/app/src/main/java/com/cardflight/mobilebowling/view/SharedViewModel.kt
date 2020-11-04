package com.cardflight.mobilebowling.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cardflight.mobilebowling.model.Roll
import kotlinx.android.synthetic.main.frame_editor_layout.view.*

class SharedViewModel : ViewModel() {
    val roll1 = MutableLiveData<String>()
    val roll2 = MutableLiveData<String>()
    val roll3 = MutableLiveData<String>()
    val frameNumber = MutableLiveData<String>()

    fun sendRoll1Score(text: String) {
        var roll = Roll(text);
    }

    fun sendRoll2Score(text: String) {
        roll2.value = text
        var roll = Roll(text);
    }

    fun sendRoll3Score(text: String) {
        roll3.value = text
        var roll = Roll(text);
    }

    fun sendFrameNumber(text: String) {
        frameNumber.value = text
        System.out.println(text)
    }
}