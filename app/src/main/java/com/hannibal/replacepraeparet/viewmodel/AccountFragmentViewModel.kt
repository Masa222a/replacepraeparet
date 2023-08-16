package com.hannibal.replacepraeparet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountFragmentViewModel : ViewModel() {
    val nameData = MutableLiveData<String>()


    fun postEditName(name: String) {
        nameData.postValue(name)
    }
}