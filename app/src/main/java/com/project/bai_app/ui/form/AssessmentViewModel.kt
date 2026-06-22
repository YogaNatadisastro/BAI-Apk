package com.project.bai_app.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.gad7.GadRequest
import com.project.bai_app.di.model.gad7.GadResponse
import com.project.bai_app.di.model.hads.HadsRequest
import com.project.bai_app.di.model.hads.HadsResponse
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.di.model.session.Patient
import com.project.bai_app.di.model.session.SessionRequest
import com.project.bai_app.di.model.session.SessionResponse
import kotlinx.coroutines.launch
import java.util.Date

class AssessmentViewModel(private val repo: MainRepository) : ViewModel() {

    private val _patientInfo = MutableLiveData<Result<SessionResponse>>()
    val patientInfo: LiveData<Result<SessionResponse>> get() = _patientInfo

    private val _gad7Result = MutableLiveData<Result<GadResponse>>()
    val gad7Result: LiveData<Result<GadResponse>> get() = _gad7Result

    private val _hadsResult = MutableLiveData<Result<HadsResponse>>()
    val hadsResult: LiveData<Result<HadsResponse>> get() = _hadsResult

    private var currentSessionId: Int? = null

    fun patientInfo(request: SessionRequest) {
        viewModelScope.launch {
            try {
                val response = repo.sessionAssessment(request)
                currentSessionId = response.sessionId
                _patientInfo.value = Result.success(response)
            } catch (e: Exception) {
                _patientInfo.value = Result.failure(e)
            }
        }
    }

    fun submitGad7(answers: List<com.project.bai_app.di.model.gad7.AnswersItem>) {
        viewModelScope.launch {
            try {
                val request = GadRequest(answers = answers, sessionId = currentSessionId)
                val response = repo.postGad7(request)

                if (response.sessionId != null) {
                    currentSessionId = response.sessionId
                }
                _gad7Result.value = Result.success(response)
            } catch (e: Exception) {
                _gad7Result.value = Result.failure(e)
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

    fun clearCurrentSession() {
        currentSessionId = null
    }
}
