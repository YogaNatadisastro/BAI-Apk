package com.project.bai_app.di.injection

import android.content.Context
import com.project.bai_app.di.api.ApiClient
import com.project.bai_app.di.model.repo.MainRepository

object Injection {
    fun provideRepo(context: Context): MainRepository {
        val apiService = ApiClient.apiService
        return MainRepository.getInstance(apiService)
    }
}