package com.project.bai_app.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.di.model.signup.SignupRequest
import com.project.bai_app.di.model.signup.SignupResponse
import kotlinx.coroutines.launch

class SignUpViewModel(private val repo: MainRepository) : ViewModel() {

    private val _signUpResult = MutableLiveData<Result<SignupResponse>>()
    val signUpResult : LiveData<Result<SignupResponse>> get() = _signUpResult

    fun signUp(
        username: String,
        email: String,
        password: String,
        adminCode: String?
    ) {
        viewModelScope.launch {
            try {
                val request = SignupRequest(
                    username = username,
                    email = email,
                    password = password,
                    adminCode = adminCode?.takeIf { it.isNotBlank() }
                )
                val response = repo.registerUser(request)
                _signUpResult.value = Result.success(response)
            } catch (e: Exception) {
                _signUpResult.value = Result.failure(e)
            }
        }
    }
}