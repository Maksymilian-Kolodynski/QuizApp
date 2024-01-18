package com.example.quizapp.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.models.Question
import com.example.quizapp.ui.components.TitleCard
import com.example.quizapp.models.Session
import java.time.LocalDateTime

@Composable
fun QuizBeginScreen(
    onNextClicked: () -> Unit,
    session: Session,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Wszystko gotowe, oto id twojego quizu:"
                )
                Text(
                    session.key
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onNextClicked() }
        ) {
            Text("Przejdź do pytań")
        }
    }
}


@Preview
@Composable
fun QuizBeginScreenPreview(){
    val ans = listOf("1","2","3","4")
    val questions = listOf(
        Question("1","Mam pytanie?", ans, 1),
        Question("1","Mam pytanie?", ans, 1),
        Question("1","Mam pytanie?", ans, 1),
        Question("1","Mam pytanie?", ans, 1),
    )
    QuizBeginScreen(
        onNextClicked = {},
        session = Session("1", "1234", LocalDateTime.now(), "quizid", questions)
    )
}