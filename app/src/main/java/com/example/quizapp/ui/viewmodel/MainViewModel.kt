package com.example.quizapp.ui.viewmodel

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.data.QuizUiState
import com.example.quizapp.data.sessionRepository
import com.example.quizapp.models.Category
import com.example.quizapp.models.Question
import com.example.quizapp.models.Session
import com.example.quizapp.networking.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(savedStateHandle.get<QuizUiState>("uiState") ?: QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var session: MutableStateFlow<Session> = MutableStateFlow(sessionRepository[0])
    private lateinit var currentQuestion: Question
    private var score = 0
    private var maxQuestionIndex = 0

    fun setSessionById(sessionKey: String): MutableStateFlow<Session> {
        try {
            runBlocking {
                session.value = Network.retrofit.getSessionByKey(sessionKey)
                maxQuestionIndex = session.value.questions.size
            }
        } catch (e: Exception) {
            Log.e(TAG, "setSessionById: ", e)
            throw e
        }
        return session
    }

    fun getSession(): Session {
        return this.session.value
    }

    fun getNextQuestion(): Question {
        if (!this.isQuizFinished()) {
            currentQuestion = session.value.questions.get(uiState.value.currentQuestionIndex)
        }
        return currentQuestion
    }

    fun isQuizFinished(): Boolean {
        if (uiState.value.currentQuestionIndex < maxQuestionIndex) {
            return false
        }
        return true
    }

    fun getQuizList(): List<Category> {
        val list: List<Category>
        runBlocking {
            list = Network.retrofit.getAllCategories()
        }
        return list
    }

    fun createSession(category: Category): MutableStateFlow<Session> {
        runBlocking {
            val response: retrofit2.Response<Void> = Network.retrofit.postNewSession(category.id)
            val key: String = response.headers()["Location"]!!.split('/').last()
            session.value = Network.retrofit.getSessionByKey(key)
        }
        maxQuestionIndex = session.value.questions.size
        return session
    }

    fun checkAnswer(ansIndex: Int) {
        uiState.value.currentQuestionIndex += 1
        if (ansIndex == currentQuestion.correct) {
            score += 1
        }
    }

    fun getUserScore(): String {
        return "$score/$maxQuestionIndex"
    }

    fun resetState() {
        _uiState.value = QuizUiState()
        score = 0
        maxQuestionIndex = 0

    }
}