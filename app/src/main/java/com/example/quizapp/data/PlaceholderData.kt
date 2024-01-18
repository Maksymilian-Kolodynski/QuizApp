package com.example.quizapp.data

import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.example.quizapp.models.Session
import java.time.LocalDateTime

val quizRepository = listOf(
    Quiz("1","Quiz 1", desc="Bardzo krótki quiz"),
    Quiz("2","Quiz 2", desc="zwykły quiz")
)

val questionRepository = listOf(
    Question("1", "2+2=?", listOf("1","2","3","4"), 3),
    Question("2", "3+2=?", listOf("5","2","3","4"), 0),
    Question("3", "2+1=?", listOf("1","2","3","4"), 2)
)

val sessionRepository = listOf(
    Session("1","test-session", LocalDateTime.now(), quizRepository[0].id, questionRepository)
)