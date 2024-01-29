package com.example.quizapp.ui.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.ui.viewmodel.MainViewModel


@Composable
fun QuizFinishedScreen(
    viewModel: MainViewModel,
    onExitClicked: () -> Unit,
    userScore: String,
    opponentScore: String,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val context = LocalContext.current
        val makeToast: (String) -> Unit = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Twój wynik:"
                )
                Text(
                    userScore
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Wynik przeciwnika:"
                )
                Text(
                    opponentScore
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.5f, true)
                .fillMaxWidth()
        ) {
            Column() {
                val text = "Pokonaj mnie w quizie \n Mój wynik: ${userScore} \n Kod quizu: ${viewModel.getSession().key}"

                Text(text = text)
                Button(onClick = {
                    val clipboard: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("label", text)
                    clipboard.setPrimaryClip(clip)
                    makeToast("Copied to clipboard")
                }) {
                    Text("skopiuj zaproszenie")
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onExitClicked() }
        ) {
            Text("Wyjdź")
        }

    }
}


@Preview
@Composable
fun QuizFinishedScreenPreview(){
    QuizFinishedScreen(
        onExitClicked = {},
        userScore = "10/10",
        opponentScore = "3/10",
        viewModel = viewModel())
}