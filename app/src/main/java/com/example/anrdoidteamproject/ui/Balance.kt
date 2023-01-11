package com.example.anrdoidteamproject.ui

import android.graphics.BlurMaskFilter
import android.util.Half.abs
import android.view.MotionEvent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.BarChartDefaults.barCornerSize
import com.example.anrdoidteamproject.ui.BarChartDefaults.barSpacing
import com.example.anrdoidteamproject.ui.BarChartDefaults.barVisualMaxThreshold
import com.example.anrdoidteamproject.ui.BarChartDefaults.barVisualMinThreshold
import com.example.anrdoidteamproject.ui.BarChartDefaults.barWidth
import com.example.anrdoidteamproject.ui.BarChartDefaults.groupBarAndLabelContainerHeight
import com.example.anrdoidteamproject.ui.BarChartDefaults.groupBarContainerHeight
import com.example.anrdoidteamproject.ui.theme.*
import kotlin.math.abs





@Composable
fun PreviewBarGraph() {
    //Preview for 3 bars per group

        Box(modifier = Modifier
            .rotate(90f)
            .padding(24.dp))
            {
            BarGraph(
                barGroups = mockedGraphData,
                onGroupSelectionChanged = {}
            )

    }
}

val mockedGraphData = listOf(
    BarGroup(
        label = "Jakub\nRoszkowski",
        values = listOf(
            //we will have value/color pairs where the value will be between 100/-100
            //we need to add 3 values so we can have 3 bars
            67 to Color(0xFF00FD84),
        )

    ),
    BarGroup(
        label = "Nataliia\nMartynenko",
        values = listOf(
            45 to Color(0xFF00FD84),
        )
    ),
    BarGroup(
        label = "Piotr\nGrygoruk",
        values = listOf(
            70 to Color(0xFF00FD84),
        )
    ),
    BarGroup(
        label = "Jan\nKowalski",
        values = listOf(

            -70 to Color(0xFFFF5858),
        )
    )
)

private object BarChartDefaults {
    const val barVisualMinThreshold = -100
    const val barVisualMaxThreshold = 100

    val barWidth = 30.dp
    val barSpacing = 1.dp
    val barCornerSize = 5.dp

    val groupBarContainerHeight = barVisualMaxThreshold.dp + kotlin.math.abs(barVisualMinThreshold).dp
    // groupBarContainerHeight + 40.dp height for the label
    val groupBarAndLabelContainerHeight = groupBarContainerHeight + 40.dp
}
@Composable
fun BarGraph(
    barGroups: List<BarGroup>,
    onGroupSelectionChanged: (index: Int) -> Unit = {}
) {



    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        barGroups.forEachIndexed { index, item ->
            if (index == 0) {
                Spacer(modifier = Modifier.weight(1f))
            }
            ChartBarGroup(
                label = item.label,
                values = item.values
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
//TODO: make names were by the other side of bar
//TODO: better to add axis
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChartBarGroup(
    modifier: Modifier = Modifier,
    label: String,
    values: List<Pair<Int, Color>>,
) {
    Column(
        modifier = modifier
            .height(groupBarAndLabelContainerHeight),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GroupLabel(
            text = label
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.height(groupBarContainerHeight), verticalAlignment = Alignment.Bottom
        ) {
            values.forEachIndexed { index, item ->
                val (realPercentage, color) = item
                val yOffset: Int
                val percentage = realPercentage.coerceIn(barVisualMinThreshold + 1, barVisualMaxThreshold - 1)

                yOffset = if (percentage >= 0) {
                    abs(barVisualMinThreshold)
                } else if (percentage in barVisualMinThreshold..-1) {
                    abs(barVisualMinThreshold) + percentage
                } else {
                    0
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ChartBar(
                        percentage = percentage,
                        color = color
                    )
                    Spacer(modifier = Modifier.height(yOffset.dp))
                }
                if (index in 0 until values.size - 1) {
                    Spacer(modifier = Modifier.width(barSpacing))
                }
            }
        }
    }
}


//TODO: add amount of expence
@Composable
private fun GroupLabel(
    text: String,
    color: Color=Color.White,
) {
    Text(
        modifier = Modifier
            .rotate(-90f)
            .padding(bottom = 8.dp),

        text = text,
        color = color,
        fontFamily = FontFamily(
            Font(R.font.century_gothic)
        ),
    )
}
fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)
@Composable
fun ChartBar(
    modifier: Modifier = Modifier,
    percentage: Int,
    color: Color,
) {
    val shape=RoundedCornerShape(topStart = if(percentage<=0) 0.dp else barCornerSize,topEnd = if(percentage<=0) 0.dp else barCornerSize,bottomStart = if(percentage<=0) barCornerSize else 0.dp,bottomEnd = if(percentage<=0) barCornerSize else 0.dp)
    val brush=if(percentage<=0) Brush.verticalGradient(listOf(color,Color(0xFF181F36))) else Brush.verticalGradient(listOf(Color(0xFF181F36),color))
    Box(
        modifier = modifier
            .clip(shape)
            .height(abs(percentage).dp)
            .width(barWidth)
            .border(1.dp,color,shape)
//            .shadow(5.dp,shape,true)
//            .shadow(Color.White,100.dp,100.dp,100.dp,100.dp,100.dp,modifier)
            .background(brush)

    )
}

data class BarGroup(
    val label: String,
    val values: List<Pair<Int, Color>>
)
@Composable
fun Balance(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    topbarButton: () -> Unit = {},
    transferFunds: () -> Unit = {},
) {

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = {
            topBar21(
                message = stringResource(R.string.historia),
                message2 = stringResource(R.string.bilans),
                onClick = topbarButton,
            )
        },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = transferFunds
            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(color = Color(0xff181f36)),
                verticalAlignment = Alignment.Top

            ) {
                PreviewBarGraph()
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(0.5f)
//                    .fillMaxHeight()
//                    .background(color = Color(0xff000000)),
//            ) {
//
//            }
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .background(color = Color(0xffffffff)),
//                ) {
//
//                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(color = Color(0xff181f36)),
                verticalAlignment = Alignment.Bottom
            ) {

            }
            //TODO: add list of payments
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color(0xff000000)),
                verticalAlignment = Alignment.Bottom
            ) {

            }
        }

    }
}


@Preview
@Composable
fun BalancePreview() {
Balance()
}
