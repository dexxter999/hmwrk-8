package com.example.something

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class SharedViewModel : ViewModel() {
    val objectAddedEvent = MutableLiveData<Boolean>()
}
