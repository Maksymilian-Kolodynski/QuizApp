package com.example.quizapp.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.quizRepository
import com.example.quizapp.models.Question
import com.example.quizapp.ui.view.QuizBeginScreen
import com.example.quizapp.ui.view.QuizFinishedScreen
import com.example.quizapp.ui.view.QuizListScreen
import com.example.quizapp.ui.view.QuizQuestionScreen
import com.example.quizapp.ui.view.QuizTypeSelectionScreen
import com.example.quizapp.ui.viewmodel.MainViewModel

enum class QuizScreen() {
    QuizTypeSelection,
    QuizList,
    QuizBegin,
    QuizQuestion,
    QuizFinished,
}

@Composable
fun QuizApp(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
) {

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    val makeToast: (String) -> Unit = {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }


    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QuizScreen.QuizTypeSelection.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = QuizScreen.QuizTypeSelection.name) {
                QuizTypeSelectionScreen(
                    onQuizSelected = {
                        navController.navigate(QuizScreen.QuizList.name)
                    },
                    onSessionSelected = {
                        // TODO pobranie sesji z api, przejscie do quizu
                        var sessionId = it
                        try {
                            viewModel.setSessionById(sessionId)
                            navController.navigate(QuizScreen.QuizQuestion.name)
                        }
                        catch (e: Exception){
                            e.message?.let { it1 -> makeToast(it1) }
                        }
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = QuizScreen.QuizBegin.name) {
                QuizBeginScreen(
                    session = viewModel.getSession(),   // TODO przekazanie obecnej sesji quizu
                    onNextClicked = {
                        navController.navigate(QuizScreen.QuizQuestion.name)
                    }
                )
            }
            composable(route = QuizScreen.QuizList.name) {
                QuizListScreen(
                    onQuizSelected = {
                        // TODO stworzenie nowej sesji, przejscie do ekranu poczatkowego
                        viewModel.createSession(it)
                        navController.navigate(QuizScreen.QuizBegin.name)
                    },
                    quizList = viewModel.getQuizList()) // TODO załadowanie listy dostepnych quizów
            }
            composable(route = QuizScreen.QuizQuestion.name) {
                QuizQuestionScreen(
                    question = viewModel.getNextQuestion(), // TODO załadowanie pytania
                    onAnswerClicked = {
                        // TODO logika tego co sie dzieje gdy zaznaczymy odpowiedź
                        val ansIndex = it
                        viewModel.checkAnswer(ansIndex)
                        if (viewModel.isQuizFinished()) {
                            navController.navigate(QuizScreen.QuizFinished.name)
                        } else {
                            navController.navigate(QuizScreen.QuizQuestion.name)
                        }
                    })
            }
            composable(route = QuizScreen.QuizFinished.name) {
                QuizFinishedScreen(
                    onExitClicked = {
                        // TODO powrót do wyboru typu quizu, viewmodel powinien sie resetowac
                        viewModel.resetState()
                        navController.navigate(QuizScreen.QuizTypeSelection.name)
                    },
                    userScore = viewModel.getUserScore(),   // TODO wyswietl wynik uzytkownika
                    opponentScore = "5/10", // TODO wyswietl wynik przeciwnika, powinno to byc w osobym procesie czy cos zeby sie moglo zaktualizowac bez odtwarzania widoku na nowo
                )
            }
        }
    }
}