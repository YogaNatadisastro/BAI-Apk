package com.project.bai_app.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.form.AnswersItem
import com.project.bai_app.di.model.form.FormRequest
import com.project.bai_app.di.model.form.FormResponse
import com.project.bai_app.di.model.repo.MainRepository
import kotlinx.coroutines.launch

class FormAdminViewModel (private val repo: MainRepository): ViewModel() {

    private val _formSubmitResult = MutableLiveData<Result<FormResponse>>()
    val formSubmitResult : LiveData<Result<FormResponse>> get() = _formSubmitResult

    fun submitForm(formInfoId: Int, answers: List<AnswersItem>) {
        viewModelScope.launch {
            try {
                val request = FormRequest(answers)
                val response = repo.postForm(formInfoId, request)
                _formSubmitResult.value = Result.success(response)
            } catch (e: Exception) {
                _formSubmitResult.value = Result.failure(e)
            }
        }
    }
}