package com.project.bai_app.di.model.repo

import com.project.bai_app.di.api.ApiClient
import com.project.bai_app.di.api.ApiService
import com.project.bai_app.di.model.gad7.GadRequest
import com.project.bai_app.di.model.hads.HadsRequest
import com.project.bai_app.di.model.login.LoginRequest
import com.project.bai_app.di.model.session.SessionRequest
import com.project.bai_app.di.model.signup.SignupRequest

class MainRepository private constructor(
    private val apiService: ApiService
){

    suspend fun loginUser(request: LoginRequest) =
        ApiClient.apiService.postLogin(request)

    suspend fun registerUser(request: SignupRequest) =
        ApiClient.apiService.postRegister(request)

    suspend fun sessionAssessment(request: SessionRequest) =
        ApiClient.apiService.startSession(request)

    suspend fun postGad7(request: GadRequest) =
        ApiClient.apiService.postGad7(request)

    suspend fun postHads(request: HadsRequest) =
        ApiClient.apiService.postHads(request)

    suspend fun getResultAll() =
        ApiClient.apiService.getResultAll()

    suspend fun getQuestionAll() =
        ApiClient.apiService.getQuestionsAll();

    companion object {
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(apiService: ApiService): MainRepository{
            return instance ?: synchronized(this) {
                instance ?: MainRepository(apiService).also { instance = it }
            }
        }
    }
}
