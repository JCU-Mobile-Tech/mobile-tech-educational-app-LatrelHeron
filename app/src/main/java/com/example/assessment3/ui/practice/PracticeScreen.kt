package com.example.assessment3.ui.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PracticeScreen(
    onBackHome: () -> Unit
) {
    val multiplicationA = remember { (2..12).random() }
    val multiplicationB = remember { (2..12).random() }

    val divisionAnswer = remember { (2..12).random() }
    val divisor = remember { (2..12).random() }
    val dividend = divisionAnswer * divisor

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Quick Practice",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Multiplication Example",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$multiplicationA × $multiplicationB = ${multiplicationA * multiplicationB}",
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Division Example",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$dividend ÷ $divisor = $divisionAnswer",
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Quick Practice does not affect your score, sessions, or rank."
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back Home")
        }
    }
}