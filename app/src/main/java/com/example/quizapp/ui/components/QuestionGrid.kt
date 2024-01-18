package com.example.quizapp.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.example.quizapp.models.Quiz
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.models.Question


@Composable
fun AnswerGrid(answerList: List<String>, onAnswerClicked: (id: Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(answerList.size) { index ->
            val item = answerList[index]
            AnswerItem(
                answer = item,
                id = index,
                onAnswerClicked = onAnswerClicked
            )
        }
    }
}

@Composable
fun AnswerItem(answer: String, id: Int, onAnswerClicked: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { onAnswerClicked(id) }),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = answer,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
            )
        }
    }
}

@Preview
@Composable
fun AnswerGridPreview() {
    val sampleAnswers = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
    )

    AnswerGrid(answerList = sampleAnswers, onAnswerClicked = {})
}