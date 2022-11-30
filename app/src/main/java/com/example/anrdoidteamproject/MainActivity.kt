package com.example.anrdoidteamproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anrdoidteamproject.ui.theme.AnrdoidTeamProjectTheme
import com.example.anrdoidteamproject.interfaces.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnrdoidTeamProjectTheme {
                // A surface container using the 'background' color from the theme
                var bscren = Settings()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    bscren.mainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var jd = BaseScreen()
    AnrdoidTeamProjectTheme {
        jd.mainScreen()
    }
}