package com.example.uas_economicnews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> get() = _text

    private val _text = MediatorLiveData<String>().apply {
        addSource(_index) { value = "Hello world from section: $it" }
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}