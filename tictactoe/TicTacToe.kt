package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TicTacToe(){

    val board = remember{
        mutableStateOf(Array(3) { arrayOfNulls<String>(3) })
    }

    val currentPlayer = remember {
        mutableStateOf("X")
    }

    val winner = remember{
        mutableStateOf<String?>(null)
    }

    val initialBoard = Array(3){ arrayOfNulls<String>(3) }
    val initialPlayer = "X"

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.inverseOnSurface)
        .padding(22.dp)){

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "Jogo da Velha", style = MaterialTheme.typography.headlineLarge)
        }

        Box(modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()){

            Column{
                for(row in 0 .. 2){
                    Row{
                        for(col in 0 .. 2){
                            Button(modifier = Modifier

                                .weight(1f)
                                .background(Color.Gray)
                                .padding(8.dp),onClick = {
                                if(board.value[row][col] == null && winner.value == null){
                                    board.value[row][col] = currentPlayer.value
                                    currentPlayer.value =
                                        if(currentPlayer.value == "X") "O" else "X"
                                    winner.value = checkWinner(board.value)


                                }
                            }) {

                                Text(
                                    text = board.value[row][col] ?: "",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = Color.White)
                            }
                        }
                    }
                }
                Text(text = "Jogador Atual: ${currentPlayer.value}", style = MaterialTheme.typography.headlineMedium,
                    color = Color.Magenta,
                    modifier = Modifier.padding(top = 16.dp
                    ))
                if(winner != null){
                    Text(text = "Vencedor ${winner.value}", style = MaterialTheme.typography.titleLarge, color = Color.Magenta,
                        modifier = Modifier.padding(10.dp))
                }
                Button(onClick = { board.value = initialBoard
                    currentPlayer.value = initialPlayer
                    winner.value = null
                }) {
                    Text(text = "Jogue Novamente")
                }
            }
        }
    }
}

fun checkWinner(board:Array<Array<String?>>):String?{
    for(row in 0 ..2){
        if(board[row][0] != null && board[row][0] == board[row][1] && board[row][1] == board[row][3]){
            return board[row][0]
        }
    }
    for(col in 0 ..2){
        if(board[0][col] != null && board[0][col] == board[1][col] && board[1][col] == board[2][col]){
            return board[0][col]
        }
    }
    if(board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
        return board[0][0]
    }

    if(board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
        return board[0][2]
    }
    return null
}