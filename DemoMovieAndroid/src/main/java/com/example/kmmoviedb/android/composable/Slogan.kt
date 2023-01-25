package com.example.kmmoviedb.android.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalTextApi::class)
@Composable
fun Slogan(modifier: Modifier) {
    Text(modifier = Modifier.then(modifier),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(0xFFFEDBD0),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp

            )){
                append("MAKE NATIVE ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    brush = Brush.linearGradient(
                        colors = GradientColors
                    ),
                    fontSize = 60.sp
                )
            ) {
                append("GREAT")
            }
            withStyle(style = SpanStyle(
                color = Color(0xFFFEDBD0),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )){
                append(" AGAIN! ")
            }
        }
    )
}

val GradientColors = listOf(
    Color.Blue,
    Color.Green,
    Color.White,
    Color.Yellow,
    Color.Magenta,
    Color.Cyan,
)