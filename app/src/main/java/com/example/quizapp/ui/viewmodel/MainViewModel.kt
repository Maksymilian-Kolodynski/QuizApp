package com.example.quizapp.ui.viewmodel

import android.net.Uri
import android.nfc.Tag
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.networking.Network
import com.example.quizapp.data.QuizUiState
import com.example.quizapp.data.questionRepository
import com.example.quizapp.data.sessionRepository
import com.example.quizapp.models.Question
import com.example.quizapp.models.Category
import com.example.quizapp.models.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import retrofit2.Call
import java.time.LocalDateTime
import kotlin.math.log

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(savedStateHandle.get<QuizUiState>("uiState") ?: QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private lateinit var session: Session
    private lateinit var currentQuestion: Question
    private var score = 0
    private var maxQuestionIndex = 0

    fun setSessionById(sessionKey: String): Session {
        for (session in sessionRepository) {
            if (session.key == sessionKey) {
                this.session = session
                this.maxQuestionIndex = session.questions.size
                return session
            }
        }
        throw Exception("Session $sessionKey not found!")
    }

    fun getSession(): Session {
        return this.session
    }

    fun getNextQuestion(): Question {
        if (!this.isQuizFinished()) {
            currentQuestion = session.questions.get(uiState.value.currentQuestionIndex)
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

    fun createSession(category: Category): Session {
        // some magic, call api to create quiz
        runBlocking {
            val response: retrofit2.Response<Void> = Network.retrofit.postNewSession(category.id)
            val key: String = response.headers()["Location"]!!.split('/').last()
            session = Network.retrofit.getSessionByKey(key)
        }
        maxQuestionIndex = questionRepository.size
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