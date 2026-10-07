package hu.ait.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.tictactoe.ui.screen.GameOverScreen
import hu.ait.tictactoe.ui.screen.GameState
import hu.ait.tictactoe.ui.screen.TicTacToeGameScreen
import hu.ait.tictactoe.ui.screen.TicTacToeViewModel
import hu.ait.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun GameScreen(modifier: Modifier, viewModel: TicTacToeViewModel = viewModel()) {
    when(viewModel.currentGameState) {
        GameState.PLAYING -> {
            TicTacToeGameScreen(modifier)
        }

        GameState.GAME_OVER -> {
            GameOverScreen(modifier)
        }
    }
}