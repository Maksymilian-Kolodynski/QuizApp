package com.example.quizapp.ui.view
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.example.quizapp.ui.components.TitleCard

@Composable
fun QuizTypeSelectionScreen(
    onQuizSelected: () -> Unit,
    onSessionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var quizId = remember  { mutableStateOf("") }

    val changeQuizId : (String) -> Unit = { it ->
        quizId.value = it
    }

    val makeToast: (String) -> Unit = {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    val checkInputAndSubmit : (String) -> Unit = {
        if (it.isNotEmpty()) {
            onSessionSelected(it)
        }
        else {
            makeToast("Wprowadź id sesji")
        }
    }

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        TitleCard("QuizApp")
        Row(
            modifier = Modifier.weight(1f, true)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { checkInputAndSubmit(quizId.value) },
                    modifier = Modifier.width(IntrinsicSize.Max),
                ) {
                    Text("Dołącz do istniejącej sesji")
                }
                TextField(
                    value = quizId.value,
                    onValueChange = changeQuizId
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onQuizSelected() },
                    modifier = Modifier.width(IntrinsicSize.Max),
                ) {
                    Text("Przejdź do listy quizów")
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizTypeSelectionScreenPreview(){
    QuizTypeSelectionScreen(
        onQuizSelected = {},
        onSessionSelected ={}
    )
}