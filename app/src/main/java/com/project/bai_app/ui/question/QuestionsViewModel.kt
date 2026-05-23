package com.project.bai_app.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.bai_app.di.model.questions.QuestionsResponseItem
import com.project.bai_app.di.model.repo.MainRepository
import kotlinx.coroutines.launch

class QuestionsViewModel(private val repo: MainRepository) : ViewModel() {

    private val _questionsList = MutableLiveData<List<QuestionsResponseItem>>()
    val questionsList: LiveData<List<QuestionsResponseItem>> get() = _questionsList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun getQuestions() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                val response = repo.getQuestionAll()
                _questionsList.value = response.questionsResponse ?: emptyList()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}