package com.hannibal.replacepraeparet.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.hannibal.replacepraeparet.model.Api.FirebaseUserLiveData

class AccountFragmentViewModel : ViewModel() {
    val nameData = MutableLiveData<String>()
    val imageData = MutableLiveData<Uri>()

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

    fun postEditImage(image: Uri) {
        imageData.postValue(image)
    }
}