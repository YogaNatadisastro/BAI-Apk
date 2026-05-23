package com.project.bai_app.ui.adminPage.submit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.bai_app.di.model.repo.MainRepository

@Suppress("UNCHECKED_CAST")
class SubmitViewModelFactory (private val repo: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmitViewModel::class.java)) {
            return SubmitViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}