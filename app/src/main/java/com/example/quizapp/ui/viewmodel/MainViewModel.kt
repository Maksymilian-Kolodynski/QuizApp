package com.example.quizapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.networking.Network
import com.example.quizapp.data.QuizUiState
import com.example.quizapp.data.questionRepository
import com.example.quizapp.data.quizRepository
import com.example.quizapp.data.sessionRepository
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.example.quizapp.models.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

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

    fun getQuizList(): List<Quiz> {
        val list: List<Quiz>
        runBlocking {
            list = Network.retrofit.getAllCategories()
        }
        return list
        //quizList = quizRepository
    }

    fun createSession(quiz: Quiz): Session {
        // some magic, call api to create quiz

        session = Session("2","new-session", LocalDateTime.now(), quiz=quiz.id, questions = questionRepository)
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