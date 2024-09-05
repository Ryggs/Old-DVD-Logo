package com.ryggs.olddvdlogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DVDLogoBouncer()
        }
    }
}

@Composable
fun DVDLogoBouncer () {
    val density = LocalDensity.current.density
    val logowidth = 100f * density
    val logoheight = 50f * density

    var position by remember { mutableStateOf(Offset(0f, 0f)) }
    var velocity by remember { mutableStateOf(Offset(2f * density, 1.5f * density)) }
    var color by remember { mutableStateOf(Color.Cyan) }

    val logo =  painterResource(id = R.drawable.dvdlogo_06)



    Canvas(modifier = Modifier.fillMaxSize()) {
        position += velocity

        if (position.x <= 0 || position.x + logowidth >= size.width) {
            velocity = velocity.copy(x = -velocity.x)
            color = generateRandomColor()
        }

        if (position.y <= 0 || position.y +  logoheight>= size.height) {
            velocity = velocity.copy(y = -velocity.y)
            color = generateRandomColor()
        }

        drawRect(Color.Black)

        drawDVDLogo(logo, position, logowidth, logoheight, color)


    }
}

fun generateRandomColor():  Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )
}
//Use SVG painter
fun DrawScope.drawDVDLogo(
    logo: androidx.compose.ui.graphics.painter.Painter,
    position: Offset,
    width: Float,
    height: Float,
    color: Color
) {
    translate(left = position.x, top = position.y) {
        with(logo) {
            draw(
                size = Size(width, height),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
            )
        }
    }
}
