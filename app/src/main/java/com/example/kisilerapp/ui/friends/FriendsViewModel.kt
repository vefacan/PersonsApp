package com.example.kisilerapp.ui.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FriendsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Friends Fragment"
    }
    val text: LiveData<String> = _text
}