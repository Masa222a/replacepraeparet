package com.hannibal.replacepraeparet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.hannibal.replacepraeparet.model.Api.FirebaseUserLiveData

class AccountFragmentViewModel : ViewModel() {
    val nameData = MutableLiveData<String>()

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    var authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    fun postEditName(name: String) {
        nameData.postValue(name)
    }
}