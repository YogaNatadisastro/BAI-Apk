package com.project.bai_app.ui.adminPage.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.gad7.GadRequest
import com.project.bai_app.di.model.gad7.GadResponse
import com.project.bai_app.di.model.hads.HadsRequest
import com.project.bai_app.di.model.hads.HadsResponse
import com.project.bai_app.di.model.info.InfoRequest
import com.project.bai_app.di.model.info.InfoResponse
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.di.model.session.Patient
import com.project.bai_app.di.model.session.SessionRequest
import com.project.bai_app.di.model.session.SessionResponse
import com.project.bai_app.di.model.signup.SignupRequest
import kotlinx.coroutines.launch

class SubmitViewModel(private val repo: MainRepository): ViewModel() {

    private val _sessionRes = MutableLiveData<Result<SessionResponse>>()
    val sessionRes: LiveData<Result<SessionResponse>> get() =  _sessionRes

    private val _gad7result = MutableLiveData<Result<GadResponse>>()
    val gad7Result: LiveData<Result<GadResponse>> get() = _gad7result

    private val _hadsResult = MutableLiveData<Result<HadsResponse>>()
    val hadsResult: LiveData<Result<HadsResponse>> get() = _hadsResult

    private var currentSessionId: Int? = null

    fun startSession(
        userId: Int,
        patient: Patient
    ) {
        viewModelScope.launch {
            try {
                val req = SessionRequest (
                    userId = userId,
                    patient = patient
                )
                val response = repo.sessionAssessment(req)
                _sessionRes.value = Result.success(response)
            } catch (e: Exception) {
                _sessionRes.value = Result.failure(e)
            }
        }
    }

    fun submitGad7(answers: List<com.project.bai_app.di.model.gad7.AnswersItem>) {
        viewModelScope.launch {
            try {
                val request = GadRequest(answers = answers, sessionId = currentSessionId)
                val response = repo.postGad7(request)
                currentSessionId = response.sessionId
                _gad7result.value = Result.success(response)
            } catch (e: Exception) {
                _gad7result.value = Result.failure(e)
            }
        }
    }

    fun submitHads(answers: List<com.project.bai_app.di.model.hads.AnswersItem>) {
        viewModelScope.launch {
            try {
                val request = HadsRequest(answers = answers, sessionId = currentSessionId)
                val response = repo.postHads(request)
                _hadsResult.value = Result.success(response)
            } catch (e: Exception) {
                _hadsResult.value = Result.failure(e)
            }
        }
    }

}