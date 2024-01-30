package com.example.quizapp.data

import com.example.quizapp.models.Question
import com.example.quizapp.models.Category
import com.example.quizapp.models.Session
import java.time.LocalDateTime

val categoryRepositories = listOf(
    Category("1","Quiz 1", "https://media.istockphoto.com/id/1415865722/pl/zdj%C4%99cie/panoramiczny-widok-na-jezioro-morskie-oko-lub-oko-morza-tatry-ko%C5%82o-zakopanego.jpg?s=612x612&w=0&k=20&c=fgdzxhJGDjNlz14-yyPA85krgDOOfcN63eArW6fNRd4=", desc="Bardzo krótki quiz"),
    Category("2","Quiz 2", null, desc="zwykły quiz")
)

val questionRepository = listOf(
    Question("1", "2+2=?", "https://e-pasje.pl/wp-content/uploads/2022/12/GRAFIKA-WEKTOROWA-860x418.jpg", listOf("1","2","3","4"), 3),
    Question("2", "3+2=?", null, listOf("5","2","3","4"), 0),
    Question("3", "2+1=?", "https://media.istockphoto.com/id/1415865722/pl/zdj%C4%99cie/panoramiczny-widok-na-jezioro-morskie-oko-lub-oko-morza-tatry-ko%C5%82o-zakopanego.jpg?s=612x612&w=0&k=20&c=fgdzxhJGDjNlz14-yyPA85krgDOOfcN63eArW6fNRd4=", listOf("1","2","3","4"), 2)
)

val sessionRepository = listOf(
    Session("1","test-session", LocalDateTime.now().toString(), categoryRepositories[0].id, questionRepository)
)