package com.example.quizapp.networking

import android.net.Uri
import com.example.quizapp.models.Category
import com.example.quizapp.models.Session
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizAPI {
    @GET("categories/all")
    suspend fun getAllCategories(): List<Category>

    @GET("categories/title/{title}")
    suspend fun getAllCategories(@Path("title") quizTitle: String): Category

    @GET("sessions/key/{key}")
    suspend fun getSessionByKey(@Path("key") key: String): Session

    @POST("sessions/new/{category_id}")
    suspend fun postNewSession(@Path("category_id") key: String): Response<Void>
}