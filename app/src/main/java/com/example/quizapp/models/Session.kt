package com.example.quizapp.models

import java.time.LocalDateTime

data class Session (
    val id:String,
    val key:String,
    val creation: LocalDateTime,
    val quiz: String,
    val questions: List<Question>
)