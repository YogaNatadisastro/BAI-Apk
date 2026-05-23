package com.project.bai_app.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.bai_app.di.model.repo.MainRepository

@Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS")
class SignUpViewModelF (private val repo: MainRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}