package com.project.bai_app.ui.adminPage.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.info.InfoRequest
import com.project.bai_app.di.model.info.InfoResponse
import com.project.bai_app.di.model.repo.MainRepository
import kotlinx.coroutines.launch

class SubmitViewModel(private val repo: MainRepository): ViewModel() {

    private val _infoSubmitResult = MutableLiveData<Result<InfoResponse>>()
    val infoSubmitResult : LiveData<Result<InfoResponse>> get() = _infoSubmitResult

    fun infoPatient(patinet_name: String, age: Int) {
        viewModelScope.launch {
            try {
                val response = repo.postInfoByAdmin(InfoRequest(patinet_name, age))
                _infoSubmitResult.value = Result.success(response)
            } catch (e: Exception) {
                _infoSubmitResult.value = Result.failure(e)
            }
        }
    }

}