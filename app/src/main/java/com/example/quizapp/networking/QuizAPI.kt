package com.example.quizapp.networking

import com.example.quizapp.models.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizAPI {
    @GET("categories/all")
    suspend fun getAllCategories(): List<Category>

    @GET("categories/title/{title}")
    suspend fun getAllCategories(@Path("title") quizTitle: String): Category
}