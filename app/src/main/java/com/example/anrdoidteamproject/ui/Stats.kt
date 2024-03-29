package com.example.anrdoidteamproject.ui

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.*
import com.example.anrdoidteamproject.ui.theme.PromptButton
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import java.lang.Math.cos
import java.lang.Math.sin


@Composable
fun DrawGradientCircles(
    navController: NavController = rememberNavController(),
    cat: Int,
    modifier: Modifier = Modifier,
    percentage: Float,
    fillColor: Color,
    backgroundColor: Brush,
    strokeWidth: Dp,
    image: Int,


    ) {
    //TODO:smaller icons
    var image = ImageBitmap.imageResource(id = image)
    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    when (cat) {
                        0 -> {
                            expensescattemp = expensescat1
                        }
                        1 -> {
                            expensescattemp = expensescat2
                        }
                        2 -> {
                            expensescattemp = expensescat3
                        }
                        3 -> {
                            expensescattemp = expensescat4
                        }
                        4 -> {
                            expensescattemp = expensescat5
                        }
                        5 -> {
                            expensescattemp = expensescat6
                        }
                    }
                    navController.navigate(AppScreens.HistoryCategory.name)
                })
            }
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
            brush = backgroundColor,
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
fun DrawGradientCircleToday(
    modifier: Modifier = Modifier,
    percentage: Float,
    backgroundColor: Brush,
    strokeWidth: Dp,
    total: String,
    navController: NavController = rememberNavController(),

) {
    val text = stringResource(id = R.string.dzisiaj)
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    expensescattemp= expensestoday
                    navController.navigate(AppScreens.HistoryCategory.name)})}
    ) {

        drawContext.canvas.nativeCanvas.apply {
            drawText(
                text,
                size.width / 2,
                size.height / 2 - 40f,
                Paint().apply {
                    textSize = 50f
                    color = Color.White.hashCode()
                    textAlign = Paint.Align.CENTER
                    //TODO:ALign text vertically
                }
            )
        }

        drawContext.canvas.nativeCanvas.apply {
            drawText(
                total,
                size.width / 2,
                size.height / 2 + 40f,
                Paint().apply {
                    textSize = 50f
                    color = Color.White.hashCode()
                    textAlign = Paint.Align.CENTER
                }
            )
        }


        // Background Line
        drawArc(
            brush = backgroundColor,
            140f,
            360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = Color(0xFF4663FF),
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
fun DrawGradientCircle(
    modifier: Modifier = Modifier,
    percentage1: Float,
    percentage2: Float,
    percentage3: Float,
    percentage4: Float,
    percentage5: Float,
    percentage6: Float,
    backgroundColor: Brush,
    strokeWidth: Dp,
    total: String,
    navController: NavController = rememberNavController()


) {


    Canvas(
        modifier = Modifier
//            .size(200.dp)
            .aspectRatio(1f)
            .padding(20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { navController.navigate(AppScreens.Balance.name) })
            }
    ) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        drawContext.canvas.nativeCanvas.apply {
            drawText(
                total,
                size.width / 2,
                size.height / 2,
                Paint().apply {
                    textSize = 75f
                    color = Color.White.hashCode()
                    textAlign = Paint.Align.CENTER
                }
            )
        }
        // Background Line
        drawArc(
            brush = Brush.horizontalGradient(listOf(Color.White, Color(0xFF181F36))),
            140f,
            360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = Color.Red,
            140f,
            percentage1 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = Color(0xFF4663FF),
            140f + (percentage1 * 360f),
            percentage2 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Color(0xFF57FF3B),
            140f + ((percentage1 + percentage2) * 360f),
            percentage3 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Color(0xFFFFC83A),
            140f + ((percentage1 + percentage2 + percentage3) * 360f),
            percentage4 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Color(0xFFFF43BE),
            140f + ((percentage1 + percentage2 + percentage3 + percentage4) * 360f),
            percentage5 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Color(0xFFA840FA),
            140f + ((percentage1 + percentage2 + percentage3 + percentage4 + percentage5) * 360f),
            percentage6 * 360f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )


        var angleInDegrees =
            ((percentage1 + percentage2 + percentage3 + percentage4 + percentage5 + percentage6) * 360.0) + 50.0
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
fun gridcat(
    percentage1: Float,
    percentage2: Float,
    percentage3: Float,
    percentage4: Float,
    percentage5: Float,
    percentage6: Float,
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage1,
                    fillColor = Color.Red,
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x40ff0000),
                            Color(0xFF181F36)
                        )
                    ),
//                    Color(android.graphics.Color.parseColor("#90A4AE")),
                    strokeWidth = 10.dp,

                    image = R.drawable.food,
                    cat = 0,
                    navController = navController
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage2,
                    fillColor = Color(0xFF4663FF),
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x404663FF),
                            Color(0xFF181F36)
                        )
                    ),
                    strokeWidth = 10.dp,
                    image = R.drawable.sleep,
                    cat = 1,
                    navController = navController
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage3,
                    fillColor = Color(0xFF57FF3B),
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x4057FF3B),
                            Color(0xFF181F36)
                        )
                    ),
                    strokeWidth = 10.dp,
                    image = R.drawable.drink,
                    cat = 2,
                    navController = navController
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxHeight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage4,
                    fillColor = Color(0xFFFFC83A),
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x40FFC83A),
                            Color(0xFF181F36)
                        )
                    ),
                    strokeWidth = 10.dp,
                    image = R.drawable.gift,
                    cat = 3,
                    navController = navController
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage5,
                    fillColor = Color(0xFFFF43BE),
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x40FF43BE),
                            Color(0xFF181F36)
                        )
                    ),
                    strokeWidth = 10.dp,
                    image = R.drawable.plane,
                    cat = 4,
                    navController = navController
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .aspectRatio(1f)
            ) {
                DrawGradientCircles(
                    percentage = percentage6,
                    fillColor = Color(0xFFA840FA),
                    backgroundColor = Brush.horizontalGradient(
                        listOf(
                            Color(0x40A840FA),
                            Color(0xFF181F36)
                        )
                    ),
                    strokeWidth = 10.dp,
                    image = R.drawable.train,
                    cat = 5,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun Stats(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    bilansButton: () -> Unit = {},
    navController: NavController = rememberNavController(),

    addExpense: () -> Unit = {},
//    tripName: String
) {
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
                onClick = addExpense,
                backgroundColor = Color(26, 51, 101)
            ) {
                Icon(
                    painterResource(id = R.drawable.add_expense_img),
                    tint = Color(0xFF5980FF),
                    contentDescription = null,

                )
//                Text(text = stringResource(R.string.zwroc_koszty), color = Color.White,
//                    fontSize = 20.sp)
            }
        },

        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.3f)
                ) {
                    if (
                        cat1foodBalanceTotal +
                        cat2sleepBalanceTotal +
                        cat3drinkBalanceTotal +
                        cat4atractionsBalanceTotal +
                        cat5planeBalanceTotal +
                        cat6transportBalanceTotal <= 1.0f
                    ) {
                        DrawGradientCircle(
                            percentage1 = cat1foodBalanceTotal,
                            percentage2 = cat2sleepBalanceTotal,
                            percentage3 = cat3drinkBalanceTotal,
                            percentage4 = cat4atractionsBalanceTotal,
                            percentage5 = cat5planeBalanceTotal,
                            percentage6 = cat6transportBalanceTotal,
                            backgroundColor = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF181F36),
                                    Color(0xFF181F36)
                                )
                            ),
                            strokeWidth = 30.dp,
                            //total = String.format("%.2f", TotalAmount)
                            total = TotalAmount.toString(), navController = navController
                        )
                    } else {
                        DrawGradientCircle(
                            percentage1 = cat1foodBalanceTotalALL,
                            percentage2 = cat2sleepBalanceTotalALL,
                            percentage3 = cat3drinkBalanceTotalALL,
                            percentage4 = cat4atractionsBalanceTotalALL,
                            percentage5 = cat5planeBalanceTotalALL,
                            percentage6 = cat6transportBalanceTotalALL,
                            backgroundColor = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF181F36),
                                    Color(0xFF181F36)
                                )
                            ),
                            strokeWidth = 30.dp,
//                            total = String.format("%.2f", TotalAmount)
                            total = TotalAmount.toString(), navController = navController
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxHeight(0.5f),
                    verticalAlignment = Alignment.CenterVertically

                )
                {
                    gridcat(
                        percentage1 = cat1foodBalance,
                        percentage2 = cat2sleepBalance,
                        percentage3 = cat3drinkBalance,
                        percentage4 = cat4atractionsBalance,
                        percentage5 = cat5planeBalance,
                        percentage6 = cat6transportBalance,
                        navController = navController
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Start)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f)
                    )
                    {
                        DrawGradientCircleToday(
                            percentage = todayBalance,
                            backgroundColor = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF181F36),
                                    Color(0x20FFFFFF)
                                )
                            ),
                            strokeWidth = 20.dp,
                            total = String.format("%.2f", today),
                            navController = navController
                        )
                    }
                    //TODO: circle button?
                    PromptButton(
                        label = R.string.bilans,
                        onClick = bilansButton
                    )
                }
            }

        }

    }
}

//@Preview
//@Composable
//fun StatsPreview() {
//    Stats(tripName = "Barcelona")
//}





