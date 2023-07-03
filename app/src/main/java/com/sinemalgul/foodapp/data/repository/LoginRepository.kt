package com.sinemalgul.foodapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sinemalgul.foodapp.data.model.User

class LoginRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    val isCurrentUserExist = MutableLiveData<Boolean>()
    val isSignIn = MutableLiveData<Pair<Boolean, String>>()
    val isSignUp = MutableLiveData<Pair<Boolean, String>>()
    val user = MutableLiveData<User>()

    private fun getFirebaseUserUid(): String = firebaseAuth.currentUser?.uid.orEmpty()

    fun signUpWithEmailAndPassword(
        user: User,
        password: String,
    ) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, password).addOnSuccessListener {
            val userModel = hashMapOf(
                "id" to getFirebaseUserUid(),
                "email" to user.email,
                "name" to user.name,
                "phone_number" to user.phoneNumber
            )

            firebaseFirestore.collection("users").document(getFirebaseUserUid()).set(userModel)
                .addOnSuccessListener {
                    isSignUp.value = Pair(true, "Sign Up Successful")
                }.addOnFailureListener {
                    isSignUp.value = Pair(false, it.message.orEmpty())
                }
        }.addOnFailureListener {
            isSignUp.value = Pair(false, it.message.orEmpty())
        }
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            isSignIn.value = Pair(true, "Sign In Successful")
        }.addOnFailureListener {
            isSignIn.value = Pair(false, it.message.orEmpty())
        }
    }

    fun getCurrentUser() {
        firebaseFirestore.collection("users").document(getFirebaseUserUid()).get()
            .addOnSuccessListener {
                user.value = User(
                    it["email"] as String,
                    it["name"] as String,
                    it["phone_number"] as String,
                )
            }.addOnFailureListener {
            }
    }

    fun checkCurrentUser() {
        isCurrentUserExist.value = firebaseAuth.currentUser != null
    }

    fun signOut() = firebaseAuth.signOut()

}