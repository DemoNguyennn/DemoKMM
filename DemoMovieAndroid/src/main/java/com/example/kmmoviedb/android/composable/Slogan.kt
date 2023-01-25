package com.example.kmmoviedb.android.composable

import androidx.compose.animation.core.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
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
    val currentFontSizePx = with(LocalDensity.current) { 20.sp.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Text(modifier = Modifier.then(modifier),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFB9E4C9),
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            ) {
                append("MAKE NATIVE ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    brush = Brush.linearGradient(
                        colors = GradientColors,
                        start = Offset(offset, offset),
                        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
                        tileMode = TileMode.Mirror
                    ),
                    fontSize = 60.sp
                )
            ) {
                append("GREAT")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFB9E4C9),
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            ) {
                append(" AGAIN! ")
            }
        }
    )
}

val GradientColors = listOf(
    Color(0xFF947C6E),
    Color(0xFFD2B4BB),
    Color(0xFFF1A311),
    Color(0xFFF9AA33),
)