package com.example.quizapp.networking

import com.example.quizapp.models.Quiz
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizAPI {
    @GET("categories/all")
    suspend fun getAllCategories(): List<Quiz>

    @GET("categories/title/{title}")
    suspend fun getAllCategories(@Path("title") quizTitle: String): Quiz
}