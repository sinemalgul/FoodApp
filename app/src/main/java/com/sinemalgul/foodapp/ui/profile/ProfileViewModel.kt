package com.sinemalgul.foodapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sinemalgul.foodapp.data.model.User
import com.sinemalgul.foodapp.data.repository.LoginRepository

class ProfileViewModel : ViewModel() {

    private val productRepository = LoginRepository()

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        productRepository.getCurrentUser()
        _user = productRepository.user
    }

    fun signOut() = productRepository.signOut()
}