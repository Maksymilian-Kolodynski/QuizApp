package com.example.quizapp.ui.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.quizapp.models.Question
import com.example.quizapp.ui.components.AnswerGrid
import com.example.quizapp.ui.components.TitleCard

@Composable
fun QuizQuestionScreen(
    question: Question,
    onAnswerClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxSize(),  // fill the maximum height of the parent
        verticalArrangement = Arrangement.Center,  // center vertically
        horizontalAlignment = Alignment.CenterHorizontally  // center horizontally
    ){
        TitleCard(question.text)
        Spacer(modifier = Modifier.width(8.dp))
        if (question.imageUrl != null) {
            AsyncImage(
                model = question.imageUrl,
                contentDescription = question.text,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        } else {
            Spacer(modifier = Modifier.size(300.dp))
        }
        Spacer(modifier = Modifier.width(8.dp))
        AnswerGrid(answerList = question.options, onAnswerClicked = onAnswerClicked)
    }
}

@Preview
@Composable
fun QuizQuestionScreenPreview(){
    val ans = listOf("1","2","3","4")
    QuizQuestionScreen(
        onAnswerClicked={},
        question = Question("1","Mam pytanie?", "null", ans, 1),
    )
}