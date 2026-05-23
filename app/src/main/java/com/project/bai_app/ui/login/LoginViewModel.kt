package com.project.bai_app.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.login.LoginRequest
import com.project.bai_app.di.model.login.LoginResponse
import com.project.bai_app.di.model.repo.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: MainRepository): ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginUser(LoginRequest(password, username))
                _loginResult.value = Result.success(response)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}