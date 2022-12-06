package com.example.anrdoidteamproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.example.anrdoidteamproject.ui.theme.AnrdoidTeamProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnrdoidTeamProjectTheme {
                MainApp(modifier = Modifier)
            }
        }
    }
}
