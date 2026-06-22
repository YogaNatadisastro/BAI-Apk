package com.project.bai_app.di.api

import com.project.bai_app.di.model.form.FormRequest
import com.project.bai_app.di.model.form.FormResponse
import com.project.bai_app.di.model.gad7.GadRequest
import com.project.bai_app.di.model.gad7.GadResponse
import com.project.bai_app.di.model.hads.HadsRequest
import com.project.bai_app.di.model.hads.HadsResponse
import com.project.bai_app.di.model.login.LoginRequest
import com.project.bai_app.di.model.login.LoginResponse
import com.project.bai_app.di.model.questions.QuestionsResponse
import com.project.bai_app.di.model.result.ResultResponse
import com.project.bai_app.di.model.session.SessionRequest
import com.project.bai_app.di.model.session.SessionResponse
import com.project.bai_app.di.model.signup.SignupRequest
import com.project.bai_app.di.model.signup.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun postLogin(@Body loginReq: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun postRegister(@Body regisReq: SignupRequest): SignupResponse

    @POST("assessment/start")
    suspend fun startSession(@Body session: SessionRequest): SessionResponse

    @GET("bai/questions")
    suspend fun getQuestionsAll(): QuestionsResponse

    @POST("forms/bai/submit")
    suspend fun postSubmit(
        @Query("form_info_id") formInfoId: Int,
        @Body request: FormRequest
    ) : FormResponse

    @POST("forms/gad7/submit")
    suspend fun postGad7(@Body request: GadRequest): GadResponse

    @POST("forms/hads/submit")
    suspend fun postHads(@Body request: HadsRequest): HadsResponse

    @GET("forms/bai/result/all")
    suspend fun getResultAll(): ResultResponse

}
