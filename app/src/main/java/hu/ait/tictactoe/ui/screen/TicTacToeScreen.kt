package hu.ait.tictactoe.ui.screen


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.tictactoe.ui.theme.Purple40


@Composable
fun TicTacToeGameScreen(modifier: Modifier,
                        viewModel: TicTacToeViewModel = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Tic-Tac-Toe",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        ScoreCard(viewModel.xWins, viewModel.oWins)

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(text = "Current Player: ${viewModel.currentPlayer}")
        TicTacToeBoard(
            board = viewModel.board,
            onCellClicked = {
                viewModel.onCellClicked(it)
            }
        )

        Button(
            onClick = {
                viewModel.resetGame()
            }
        ) {
            Text("Reset")
        }
    }
}



@Composable
fun ScoreCard(
    xScore: Int,
    oScore: Int,
    modifier: Modifier = Modifier
) {
    val xColor = MaterialTheme.colorScheme.onSurface
    val oColor = Purple40

    Card(
        modifier = modifier.fillMaxWidth(0.8f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // X on the left
            Canvas(modifier = Modifier.size(32.dp)) {
                val inset = size.width * 0.2f
                val thickness = size.width * 0.18f

                drawLine(
                    color = xColor,
                    start = Offset(inset, inset),
                    end = Offset(size.width - inset, size.height - inset),
                    strokeWidth = thickness,
                    cap = StrokeCap.Butt
                )
                drawLine(
                    color = xColor,
                    start = Offset(size.width - inset, inset),
                    end = Offset(inset, size.height - inset),
                    strokeWidth = thickness,
                    cap = StrokeCap.Butt
                )
            }

            // Scores in the middle
            Text(
                text = "$xScore : $oScore",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            // O on the right
            Canvas(modifier = Modifier.size(32.dp)) {
                drawCircle(
                    color = oColor,
                    center = center,
                    radius = size.minDimension * 0.32f,
                    style = Stroke(width = size.minDimension * 0.18f)
                )
            }
        }
    }
}