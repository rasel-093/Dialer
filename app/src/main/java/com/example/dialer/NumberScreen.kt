package com.example.dialer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DialPadScreen(onNumberClick: (String) -> Unit) {
    Column(
        modifier = Modifier
//            .fillMaxSize()
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        for (i in 1..3) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                for (j in 1..3) {
                    val number = (i - 1) * 3 + j
                    DialPadButton(
                        text = number.toString(),
                        onClick = { onNumberClick(number.toString()) }
                    )
                }
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            DialPadButton(
                text = "*",
                onClick = { onNumberClick("*") }
            )
            DialPadButton(
                text = "0",
                onClick = { onNumberClick("0") }
            )
            DialPadButton(
                text = "#",
                onClick = { onNumberClick("#") }
            )
        }
    }
}

@Composable
fun DialPadButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp),
        shape = CircleShape
    ) {
        NumberText(text = text)
    }
}

@Composable
fun NumberText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.Black
    )
}
