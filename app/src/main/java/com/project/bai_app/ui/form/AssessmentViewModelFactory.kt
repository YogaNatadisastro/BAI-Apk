package com.project.bai_app.ui.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.bai_app.di.injection.Injection
import com.project.bai_app.di.model.repo.MainRepository

class AssessmentViewModelFactory(private val repo: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssessmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssessmentViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: AssessmentViewModelFactory? = null

        fun getInstance(context: Context): AssessmentViewModelFactory {
            return instance ?: synchronized(this) {
                val repo = Injection.provideRepo(context)
                instance ?: AssessmentViewModelFactory(repo).also { instance = it }
            }
        }
    }
}
