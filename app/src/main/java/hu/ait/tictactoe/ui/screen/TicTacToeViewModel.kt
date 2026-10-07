package hu.ait.tictactoe.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

enum class Player { X, O}

enum class GameState {
    PLAYING,
    GAME_OVER
}

data class BoardCell(val row: Int, val col: Int)
class TicTacToeViewModel : ViewModel() {

    var currentGameState by mutableStateOf(GameState.PLAYING)

    var currentPlayer by mutableStateOf(Player.O)
    var winner by mutableStateOf("")

    var xWins by mutableStateOf(0)
    var oWins by mutableStateOf(0)

    val totalPlays = 2
    var countPlays = 0

    var board by mutableStateOf(
        Array(3) {
            Array(3) {
                null as Player?
            }
        }
    )

    var totalMoves = 0

    /*init {
        board[2][1] = Player.X
    }*/

    fun onCellClicked(cell: BoardCell) {
        if (board[cell.row][cell.col] != null) return

        totalMoves++ //count plays used

        val newBoard = board.copyOf()
        newBoard[cell.row][cell.col] = currentPlayer
        board = newBoard // state change

        if (checkWin(cell.row, cell.col)) {

            if (currentPlayer == Player.X) {
                xWins += 1
            } else {
                oWins += 1
            }

            countPlays += 1
            resetGame()
        } else {
            currentPlayer = if (currentPlayer == Player.X)
                Player.O else Player.X
        }

        if (totalMoves == (board.size * board.size)) {
            countPlays += 1
            resetGame()
        }

        if (countPlays == totalPlays) {
            onGameOver()
        }
    }
    fun resetGame() {
        board = Array(3) { Array(3) { null as Player? } }
        winner = ""
        totalMoves = 0
        currentPlayer = Player.X
    }

    fun scoreWinner() {
        winner = if (xWins > oWins) {
            Player.X.toString()
        } else if (oWins > xWins){
            Player.O.toString()
        } else {
            "tie!"
        }
    }

    val onGameOver: () -> Unit = {
        scoreWinner()
        currentGameState = GameState.GAME_OVER
    }

    val onStartGame: () -> Unit = {
        resetGame()
        countPlays  = 0
        oWins = 0
        xWins = 0
        currentGameState = GameState.PLAYING
    }


    private fun checkWin(cellX: Int, cellY: Int): Boolean {
        //checks win for current player
        // player possible win states
        // vertical from current position
        //horizontal from current position
        //diagonal from current position

        //check horizontal
        var isHorizontal = true
        for (i in 0 until board.size) {
            if (board[i][cellY] != currentPlayer) isHorizontal = false
            Log.d("CHECK_PLAYER", "isHorizontal: $isHorizontal  pos:$i, $cellY")
        }

        // check vertical
        var isVertical = true
        for (j in 0 until board.size) {
            if(board[cellX][j] != currentPlayer) isVertical = false
            Log.d("CHECK_PLAYER", "isVertical: $isVertical  pos:$j, $cellY")
        }

        //diagonal from left to right downwards, only if the cell is on it
        var isDownDiagonal = true
        //diagonal from left to right upwards, only if the cell is on it
        var isUpDiagonal = true
        for( i in 0 until board.size) {
            Log.d("CHECK_PLAYER", "isDownDiagonal: $isDownDiagonal pos:$i,$i, isUpDiagonal: $isUpDiagonal pos:${board.size - 1 - i},$i")
            if(board[i][i] != currentPlayer) isDownDiagonal = false
            if(board[board.size - 1 - i][i] != currentPlayer) isUpDiagonal = false
        }

        return isVertical or isHorizontal or isDownDiagonal or isUpDiagonal
    }
}