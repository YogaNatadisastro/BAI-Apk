package com.project.bai_app.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.bai_app.di.injection.Injection
import com.project.bai_app.di.model.repo.MainRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelF (private val repo: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

    companion object {
        @Volatile
        private var instance: LoginViewModelF? = null

        fun getInstance(context: Context): LoginViewModelF {
            return instance ?: synchronized(this) {
                val repo = Injection.provideRepo(context)
                instance ?: LoginViewModelF(repo).also { instance = it }
            }
        }
    }
}