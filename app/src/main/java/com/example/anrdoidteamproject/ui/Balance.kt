package com.example.anrdoidteamproject.ui

import android.util.Half.abs
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.*


@Composable
fun ChartBar(
    modifier: Modifier = Modifier,
    percentage: Int,
    brush: Brush,
) {
    Box(
        modifier = modifier
            .clip(RectangleShape)
            .height(kotlin.math.abs(percentage).dp)
            .width(30.dp)
            .background(brush)
            .background(color = Color.Black.copy(alpha = 0.5f))
    )
}

@Composable
private fun GroupLabel(
    text: String,
    color: Color = Color(0x40ffffff),
//    textStyle: TextStyle = MaterialTheme.typography.,
    isHighlighted: Boolean = false
) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = text,
        color = color,
    )
}
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
                    .background(color = Color(0xffffffff)),
                verticalAlignment = Alignment.Top

            ) {
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
