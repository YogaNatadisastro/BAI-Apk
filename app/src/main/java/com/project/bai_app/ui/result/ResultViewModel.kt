package com.project.bai_app.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.di.model.result.ResultResponseItem
import kotlinx.coroutines.launch

class ResultViewModel (private val repo: MainRepository) : ViewModel() {

    private val _resultBAIList = MutableLiveData<List<ResultResponseItem>>()
    val resultBAIList: LiveData<List<ResultResponseItem>> get() = _resultBAIList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun getResultBai() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val response = repo.getResultAll()
                _resultBAIList.value = response.resultResponse ?: emptyList()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}