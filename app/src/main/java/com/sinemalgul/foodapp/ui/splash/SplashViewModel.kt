package com.sinemalgul.foodapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.repository.LoginRepository

class SplashViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private var _isCurrentUserExist = MutableLiveData<Boolean>()
    val isCurrentUserExist: LiveData<Boolean>
        get() = _isCurrentUserExist

    init {
        _isCurrentUserExist = loginRepository.isCurrentUserExist
    }

    fun checkCurrentUser() = loginRepository.checkCurrentUser()
}