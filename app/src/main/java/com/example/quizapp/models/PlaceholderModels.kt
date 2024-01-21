package com.example.quizapp.models

import java.time.LocalDateTime

data class Quiz  (
    val id:String,
    val title:String,
    val imageUrl:String?,
    val desc:String,
)

data class Question (
    val id:String,
    val text:String,
    val imageUrl:String?,
    val options:List<String>,
    val correct:Int,
)

data class Session (
    val id:String,
    val key:String,
    val creation: LocalDateTime,
    val quiz: String,
    val questions: List<Question>
    )