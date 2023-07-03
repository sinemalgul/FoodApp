package com.sinemalgul.foodapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.model.User
import com.sinemalgul.foodapp.data.repository.LoginRepository

class SignUpViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private var _isSignUp = MutableLiveData<Pair<Boolean, String>>()
    val isSignUp: LiveData<Pair<Boolean, String>>
        get() = _isSignUp

    init {
        _isSignUp = loginRepository.isSignUp
    }

    fun signUp(user: User, password: String) =
        loginRepository.signUpWithEmailAndPassword(user, password)
}