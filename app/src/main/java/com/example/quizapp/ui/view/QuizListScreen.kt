package com.example.quizapp.ui.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.models.Category
import com.example.quizapp.ui.components.QuizCard
import com.example.quizapp.ui.components.TitleCard

@Composable
fun QuizListScreen(
    onQuizSelected: (Category) -> Unit,
    categoryList: List<Category>,
    modifier: Modifier = Modifier
) {
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
                for (quiz in categoryList){
                    QuizCard(quiz, onQuizSelected)
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizListScreenPreview(){
    QuizListScreen(
        onQuizSelected={},
        categoryList = listOf(Category("1","tytuł", "https://media.istockphoto.com/id/1415865722/pl/zdj%C4%99cie/panoramiczny-widok-na-jezioro-morskie-oko-lub-oko-morza-tatry-ko%C5%82o-zakopanego.jpg?s=612x612&w=0&k=20&c=fgdzxhJGDjNlz14-yyPA85krgDOOfcN63eArW6fNRd4=", "opis"), Category("2","quiz", null,"opiss"))
    )
}