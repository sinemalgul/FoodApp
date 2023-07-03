package com.sinemalgul.foodapp.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.repository.LoginRepository

class SignInViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private var _isSignIn = MutableLiveData<Pair<Boolean, String>>()
    val isSignIn: LiveData<Pair<Boolean, String>>
        get() = _isSignIn

    init {
        _isSignIn = loginRepository.isSignIn
    }

    fun signIn(email: String, password: String) =
        loginRepository.signInWithEmailAndPassword(email, password)
}