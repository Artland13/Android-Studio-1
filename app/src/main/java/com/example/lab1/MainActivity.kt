package com.example.lab1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { MainScreen() }
        }
    }
}

@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("EXTRA_TEXT", text)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Открыть вторую Activity") }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (text.isBlank()) return@Button
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${text.trim()}")
                }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Позвонить другу") }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (text.isBlank()) return@Button
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                context.startActivity(Intent.createChooser(intent, "Поделиться через..."))
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Поделиться текстом") }
    }
}