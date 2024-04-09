package com.example.pokedex.presentation.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.ui.components.common.CircularShape
import com.example.pokedex.ui.components.common.CoilImageWithUrl
import com.example.pokedex.ui.theme.blue
import com.example.pokedex.ui.theme.circularShapeBackground
import com.example.pokedex.ui.theme.green
import com.example.pokedex.ui.theme.red
import com.example.pokedex.ui.theme.water
import com.example.pokedex.ui.theme.watergrass
import com.example.pokedex.ui.theme.yellow
import com.example.pokedex.util.noRippleClickable
import kotlinx.coroutines.delay

@Composable
fun DetailFragment(pokemon: Pokemon?, onBack: () -> Unit = {}) {
    val scrollState = rememberScrollState(0)
    val gradientColors = listOf(water, watergrass)
    val brush = Brush.verticalGradient(gradientColors)
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = ""
    )
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 18.dp, top = 15.dp)
                        .size(40.dp)
                        .noRippleClickable { onBack() })
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 21.dp, top = 15.dp)
                        .size(32.dp)
                        .noRippleClickable {})
            }
            CircularShape(modifier = Modifier
                .size(280.dp)
                .drawBehind {
                    rotate(rotationAnimation.value) {
                        drawCircle(
                            brush = brush, style = Stroke(15f)
                        )
                    }
                }) {
                CoilImageWithUrl(
                    imageUrl = pokemon?.imageUrl ?: "",
                    modifier = Modifier.padding(30.dp)
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = pokemon?.name ?: "No pokemon selected",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                TypeChip("Water")
                TypeChip("Fire")
                TypeChip("Grass")
            }
            Spacer(modifier = Modifier.size(30.dp))
            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = circularShapeBackground
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(9.dp))
                    AbilityIndicator("HP", 0.73f, position = 1, color = red)
                    AbilityIndicator("Armor", 0.32f, position = 2, color = yellow)
                    AbilityIndicator("Speed", 0.54f, position = 3, color = green)
                    AbilityIndicator("Magic", 0.91f, position = 4, color = blue)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "About",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 1.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    ExpandingText(
                        text = "Ivysaur is a Grass/Poison-type Pokémon, known as the second stage of Bulbasaur's evolutionary line. It retains some of the characteristics of its initial form while displaying distinct growth and changes. Standing at around 1 meter (3 feet) tall, Ivysaur exhibits a more developed and robust appearance.\n" +
                                "\n" +
                                "Its body is predominantly bluish-green, with a bulb-like structure on its back that continues to grow from where it was as a Bulbasaur. This bulb is now larger and more vibrant, featuring a pinkish-red blossom on top that is gradually blooming. The blossom serves as a way for Ivysaur to gather energy from sunlight, which it utilizes both for nourishment and as a source of power during battles."
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun AbilityIndicator(ability: String, progress: Float = 0.7f, position: Int, color: Color) {
    var intProgress by remember { mutableStateOf(0) }
    val progressAnimDuration = 960
    val progressAnimation by animateIntAsState(
        targetValue = intProgress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = ""
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(end = 1.dp, bottom = 4.dp, start = 2.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = ability, modifier = Modifier
                .padding(end = 10.dp)
        )
        CustomLinearProgressBar(progress, position, modifier = Modifier.weight(3f), color)
        Text(
            text = progressAnimation.toStringWithTwoDigits(), modifier = Modifier
                .padding(end = 1.dp)
        )
    }

    LaunchedEffect(true) {
        delay((progressAnimDuration.toLong() - 500) * position)
        intProgress = progress.times(100).toInt()
    }
}

private fun Int.toStringWithTwoDigits(): String =
    if (this < 10) "0$this" else this.toString()

@Composable
fun ExpandingText(text: String) {
    var expanded by remember { mutableStateOf(true) }
    Text(
        text = text,
        overflow = TextOverflow.Ellipsis,
        maxLines = if (expanded) Int.MAX_VALUE else 4,
        modifier = Modifier
            .padding(start = 1.dp)
            .noRippleClickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    )
}

@Composable
private fun CustomLinearProgressBar(
    indicatorProgress: Float,
    position: Int,
    modifier: Modifier = Modifier,
    color: Color
) {
    RoundedLinearProgress(
        animateNumber = indicatorProgress * 100, position = position, modifier = modifier,
        gradientColors = listOf(color, color)
    )
}

@Composable
fun TypeChip(text: String = "Water") {
    val gradientColors = listOf(water, watergrass)
    val brush = Brush.horizontalGradient(gradientColors)
    val color = when (text) {
        "Water" -> water
        "Fire" -> yellow
        "Grass" -> green
        else -> yellow
    }

    Box(
        modifier = Modifier
            .widthIn()
            .border(1.dp, color, RoundedCornerShape(7.dp))
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp),
            style = TextStyle(
                color = color
            )
        )
    }
}

@Composable
fun RoundedLinearProgress(
    indicatorHeight: Dp = 12.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.3f),
    indicatorPadding: Dp = 15.dp,
    gradientColors: List<Color> = listOf(
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7),
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7)
    ),
    animateNumber: Float,
    position: Int,
    modifier: Modifier
) {
    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 960
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = ""
    )
    Canvas(
        modifier = modifier
            .height(indicatorHeight)
            .padding(start = indicatorPadding, end = indicatorPadding)
    ) {

        // Background indicator
        drawLine(
            color = backgroundIndicatorColor,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = size.height / 2),
            end = Offset(x = size.width, y = size.height / 2)
        )
        val progress =
            (progressAnimation / 100) * size.width // size.width returns the width of the canvas

        // Foreground indicator
        drawLine(
            brush = Brush.linearGradient(
                colors = gradientColors
            ),
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = size.height / 2),
            end = Offset(x = progress, y = size.height / 2)
        )

    }
    LaunchedEffect(true) {
        delay((progressAnimDuration.toLong() - 500) * position)
        progress = animateNumber
    }
}

@Preview
@Composable
fun DetailFragment() {
    Surface {
        DetailFragment(
            pokemon = Pokemon(
                1,
                "Bulbasaur"
            )
        )
    }

}