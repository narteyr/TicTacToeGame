package hu.ait.tictactoe.ui.screen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import hu.ait.tictactoe.ui.theme.Purple40


@Composable
fun TicTacToeBoard(
    board: Array<Array<Player?>>,
    onCellClicked: (BoardCell) -> Unit
) {
    val xColor = MaterialTheme.colorScheme.onSurface
    val gridColor = MaterialTheme.colorScheme.outline
    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(
                width = 10.dp,
                color = gridColor
            )
            .aspectRatio(1.0f) // adjust height to match with the width
            .pointerInput(key1 = Unit) {
                detectTapGestures {
                        offSet ->
                    //Log.d("TAG_TAP",
                    //    "${offSet.x} - ${offSet.y}")

                    val row = (offSet.y / (size.height / 3)).toInt()
                    val col = (offSet.x / (size.width / 3)).toInt()
                    onCellClicked(BoardCell(row,col))
                }
            }
    ) {
        // --- Draw the grid
        val gridSize = size.minDimension
        val thirdSize = gridSize / 3

        for (i in 1..2) {
            drawLine(
                color = gridColor,
                strokeWidth = 10f,
                start = Offset(thirdSize * i, 0f),
                end = Offset(thirdSize * i, gridSize)
            )

            drawLine(
                color = gridColor,
                strokeWidth = 10f,
                start = Offset(0f, thirdSize * i),
                end = Offset(gridSize, thirdSize * i)
            )
        }

        // --- Draw the players
        for (row in 0..2) {
            for (col in 0..2) {
                val player = board[row][col]
                if (player != null) {
                    val centerX = col * thirdSize + thirdSize / 2
                    val centerY = row * thirdSize + thirdSize / 2
                    val arm = thirdSize * 0.22f
                    val thickness = thirdSize * 0.18f

                    if (player == Player.X) {
                        // Top-left to bottom-right
                        drawLine(
                            color = xColor,
                            strokeWidth = thickness,
                            cap = StrokeCap.Butt,
                            start = Offset(centerX - arm, centerY - arm),
                            end = Offset(centerX + arm, centerY + arm)
                        )

                        // Top-right to bottom-left
                        drawLine(
                            color = xColor,
                            strokeWidth = thickness,
                            cap = StrokeCap.Butt,
                            start = Offset(centerX + arm, centerY - arm),
                            end = Offset(centerX - arm, centerY + arm)
                        )
                    } else {
                        drawCircle(
                            color = Purple40,
                            style = Stroke(width = 50f),
                            center = androidx.compose.ui.geometry.Offset(centerX, centerY),
                            radius = thirdSize / 4,
                        )
                    }
                }
            }
        }

    }
}