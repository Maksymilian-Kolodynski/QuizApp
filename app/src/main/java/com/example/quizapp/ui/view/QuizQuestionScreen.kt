package com.example.quizapp.ui.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.example.quizapp.ui.components.AnswerGrid
import com.example.quizapp.ui.components.QuizCard
import com.example.quizapp.ui.components.TitleCard

@Composable
fun QuizQuestionScreen(
    question: Question,
    onAnswerClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
//        modifier = modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.SpaceEvenly
    ){
        TitleCard(question.text)
        // TODO dodać zdjęcia do pytań z quizu?
        AnswerGrid(answerList = question.options, onAnswerClicked = onAnswerClicked)
    }
}

@Preview
@Composable
fun QuizQuestionScreenPreview(){
    val ans = listOf("1","2","3","4")
    QuizQuestionScreen(
        onAnswerClicked={},
        question = Question("1","Mam pytanie?", ans, 1),
    )
}