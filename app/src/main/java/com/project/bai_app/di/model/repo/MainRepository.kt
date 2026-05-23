package com.project.bai_app.di.model.repo

import com.project.bai_app.di.api.ApiClient
import com.project.bai_app.di.model.form.FormRequest
import com.project.bai_app.di.model.form.FormResponse
import com.project.bai_app.di.model.gad7.GadRequest
import com.project.bai_app.di.model.hads.HadsRequest
import com.project.bai_app.di.model.info.InfoRequest
import com.project.bai_app.di.model.login.LoginRequest
import com.project.bai_app.di.model.signup.SignupRequest

class MainRepository {

    suspend fun loginUser(request: LoginRequest) =
        ApiClient.apiService.postLogin(request)

    suspend fun registerUser(request: SignupRequest) =
        ApiClient.apiService.postRegister(request)

    suspend fun postInfoByAdmin(request: InfoRequest) =
        ApiClient.apiService.postInfo(request)

    suspend fun postForm(
        formInfoId: Int,
        request: FormRequest
    ): FormResponse = ApiClient.apiService.postSubmit(formInfoId, request)

    suspend fun postGad7(request: GadRequest) =
        ApiClient.apiService.postGad7(request)

    suspend fun postHads(request: HadsRequest) =
        ApiClient.apiService.postHads(request)

    suspend fun getResultAll() =
        ApiClient.apiService.getResultAll()

    suspend fun getQuestionAll() =
        ApiClient.apiService.getQuestionsAll();
}
