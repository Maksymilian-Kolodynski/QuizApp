package com.example.quizapp.models

data class Question (
    val id:String,
    val text:String,
    val imageUrl:String?,
    val options:List<String>,
    val correct:Int,
)