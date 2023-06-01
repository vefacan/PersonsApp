package com.example.kisilerapp.ui.colleagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColleaguesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Colleagues Fragment"
    }

    val text: LiveData<String> = _text

}