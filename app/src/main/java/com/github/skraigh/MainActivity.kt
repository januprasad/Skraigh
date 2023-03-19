package com.github.skraigh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.skraigh.ui.theme.PurpleGrey40
import com.github.skraigh.ui.theme.PurpleGrey80
import com.github.skraigh.ui.theme.SkraighTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkraighTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        UI()
                    }
                }
            }
        }
    }
}

@Composable
private fun animateColorBetween(
    initialValue: Color,
    targetValue: Color,
): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse,
        ),
    )
}

@Composable
fun UI(componentSize: Dp = 300.dp) {
    val color by animateColorBetween(PurpleGrey40, PurpleGrey80)
    val animatedDotScale by animateSizeBetween()
    Canvas(
        modifier = Modifier
            .size(componentSize),
    ) {
        drawCircle(
            color = PurpleGrey40,
            radius = size.width,
        )

        drawCircle(
            color = color,
            center = Offset(x = size.width / 2f, y = size.height / 2f),
            radius = animatedDotScale,
        )
    }
}

@Composable
private fun animateSizeBetween(
    componentSize: Dp = 200.dp,
    initialValue: Float = 10.0f,
): State<Float> {
    val canvasSizePx = with(LocalDensity.current) {
        componentSize.toPx()
    }
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = canvasSizePx / 2.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000),
            repeatMode = RepeatMode.Reverse,
        ),
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkraighTheme {
        Greeting("Android")
    }
}
