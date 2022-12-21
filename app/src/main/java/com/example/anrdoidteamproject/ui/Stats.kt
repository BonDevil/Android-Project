package com.example.anrdoidteamproject.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.key.Key.Companion.H
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.Actionbuton2
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import java.lang.Math.cos
import java.lang.Math.sin


@Composable
fun DrawGradientCircles(
    modifier: Modifier = Modifier,
    percentage: Float,
    fillColor: Color,
    backgroundColor: Color,
    strokeWidth: Dp,
    image: Int

) {
    val image = ImageBitmap.imageResource(id = image)

    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(20.dp)
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawImage(
            image = image,
            topLeft = Offset(
                x = ((canvasWidth / 2) - (image.width / 2)),
                y = ((canvasHeight / 2) - (image.height / 2))
            ),

            )

        // Background Line
        drawArc(
            color = backgroundColor,
            140f,
            360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = fillColor,
            140f,
            percentage * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )


        var angleInDegrees = (percentage * 360.0) + 50.0
        var radius = (size.height / 2)
        var x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        var y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

        drawCircle(
            color = Color.White,
            radius = 5f,
            center = Offset(x, y)
        )
    }
}

@Composable
fun gridcat(){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row() {
            DrawGradientCircles(
                percentage = 0.80f,
                fillColor = Color.Red,
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.food
            )
            DrawGradientCircles(
                percentage = 0.40f,
                fillColor = Color.Blue,
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.sleep
            )
            DrawGradientCircles(
                percentage = 0.60f,
                fillColor = Color.Green,
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.drink
            )
        }
        Row() {
            DrawGradientCircles(
                percentage = 0.20f,
                fillColor = Color.Yellow,
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.gift
            )
            DrawGradientCircles(
                percentage = 0.30f,
                fillColor = Color(0xFFFF43BE),
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.plane
            )
            DrawGradientCircles(
                percentage = 0.40f,
                fillColor = Color(0xFFA840FA),
                backgroundColor = Color(android.graphics.Color.parseColor("#90A4AE")),
                strokeWidth = 10.dp,
                image = R.drawable.train
            )
        }
    }
}

@Composable
fun Stats(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},

    addExpense: () -> Unit = {},
    addCategory: () -> Unit = {},
    tripName: String
) {
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = tripName) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = !expanded },
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_action_more_buttons),
                    tint = Color.Black,
                    contentDescription = "more",
                )
            }

            if (expanded) {
                Actionbuton2(
                    onClick = { expanded = !expanded },
                    onClick1 = addExpense,
                    onClick2 = addCategory,
                    drawable = R.drawable.img_dolar,
                    drawable2 = Icons.Filled.Add
                )
            }
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            gridcat()
        }
    }
}

@Preview
@Composable
fun StatsPreview() {
    Stats(tripName = "Barcelona")
}





