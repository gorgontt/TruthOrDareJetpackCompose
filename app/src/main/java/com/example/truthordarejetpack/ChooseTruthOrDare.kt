package com.example.truthordarejetpack

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.truthordarejetpack.couplelists.CoupleExtremeTruthList
import com.example.truthordarejetpack.couplelists.CoupleHardTruthList
import com.example.truthordarejetpack.couplelists.CoupleHotTruthList
import com.example.truthordarejetpack.couplelists.CoupleSoftTruthList
import com.example.truthordarejetpack.ui.theme.DarkGray
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.LightGray
import com.example.truthordarejetpack.ui.theme.Orange
import com.example.truthordarejetpack.ui.theme.Pink
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.ShadowGreen
import com.example.truthordarejetpack.ui.theme.ShadowViolet
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.Violet
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.delay
import java.text.BreakIterator
import java.text.StringCharacterIterator
import kotlin.random.Random

@Composable
fun ChooseTruthOrDare(type: String?, modeType: String?, playersList: List<String>, onDismiss: () -> Unit, navController: NavController) {
    val brush = Brush.linearGradient(listOf(Gray, DarkGray))

    // Выбор случайного имени игрока
    val randomPlayerName = if (playersList.isNotEmpty()) {
        val randomIndex = remember { Random.nextInt(playersList.size) }
        playersList[randomIndex]
    } else {
        "Нет игроков"
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .background(brush),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().background(Transpar),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(onClick = { onDismiss() }) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp),
                        painter = painterResource(id = R.drawable.arrow_back),
                        tint = Color.White,
                        contentDescription = "Back"
                    )
                }

                Text(
                    modifier = Modifier
                        .offset(28.dp, 28.dp)
                        .fillMaxHeight(0.15f)
                        .fillMaxWidth(0.7f),
                    text = randomPlayerName,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White, fontSize = 40.sp, fontFamily = FontFamily(
                            Font(R.font.jura_semibold)
                        )
                    )
                )
            }


            Card(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Green,
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp)
                    )
                    .animatedBorder(
                        borderColors = listOf(Orange, Green),
                        backgroundColor = Gray,
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                        borderWidth = 4.dp
                    )
                    .background(Transpar)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.4f)
                    .shadow(
                        elevation = 7.dp,
                        ambientColor = Color.Black,
                        spotColor = Color.Black,
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp), color = ShadowGreen,
                        offsetY = (-8).dp, offsetX = (-8).dp
                    )
                    // Top left corner shadow.
                    .innerShadow(
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp), color = ShadowGreen,
                        offsetY = 8.dp, offsetX = 8.dp
                    )
                    .clickable {
                        navController.navigate("question/$type/$modeType/правда/$randomPlayerName")
                    },
                shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                ) {

                    Image(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(20.dp)
                            .animatedBorder(
                                borderColors = listOf(Orange, Green),
                                backgroundColor = Gray,
                                shape = RoundedCornerShape(30.dp),
                                borderWidth = 4.dp
                            ),
                        painter = painterResource(id = R.drawable.truth_icon),
                        contentDescription = "TruthIcon"
                    )

                    Text(
                        text = "Правда",
                        style = TextStyle(
                            color = Green, fontSize = 50.sp, fontFamily = FontFamily(
                                Font(R.font.juraa)
                            )
                        )
                    )
                }
            }

            Card(
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .border(
                        width = 2.dp,
                        color = Violet,
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp)
                    )
                    .animatedBorder(
                        borderColors = listOf(Pink, Violet),
                        backgroundColor = Gray,
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                        borderWidth = 4.dp
                    )
                    .background(Transpar)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.7f)
                    .shadow(
                        elevation = 4.dp,
                        ambientColor = Color.Black,
                        spotColor = Color.Black,
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp), color = ShadowViolet,
                        offsetY = (-8).dp, offsetX = (-8).dp
                    )
                    // Top left corner shadow.
                    .innerShadow(
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp), color = ShadowViolet,
                        offsetY = 8.dp, offsetX = 8.dp
                    )
                    .clickable {
                        navController.navigate("question/$type/$modeType/действие/$randomPlayerName")
                    },
                shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                ) {

                    Image(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(20.dp)
                            .animatedBorder(
                                borderColors = listOf(Pink, Violet),
                                backgroundColor = Gray,
                                shape = RoundedCornerShape(30.dp),
                                borderWidth = 4.dp
                            ),
                        painter = painterResource(id = R.drawable.dare_icon),
                        contentDescription = "DareIcon"
                    )

                    Text(
                        text = "Действие",
                        style = TextStyle(
                            color = Violet, fontSize = 50.sp, fontFamily = FontFamily(
                                Font(R.font.juraa)
                            )
                        )
                    )
                }
            }

        }
    }
}


@Composable
fun Question(type: String?, modeType: String?, typeTD: String?, playerName: String?, navController: NavController){

    val brush = Brush.linearGradient(listOf(Gray, DarkGray))
    var boxVisible by rememberSaveable { mutableStateOf(true)}
    val question = when {
        type == "пара" && typeTD == "правда" && modeType == "soft" -> CoupleSoftTruthList.random()
        type == "пара" && typeTD == "правда" && modeType == "hot" -> CoupleHotTruthList.random()
        type == "пара" && typeTD == "правда" && modeType == "hard" -> CoupleHardTruthList.random()
        type == "пара" && typeTD == "правда" && modeType == "extreme" -> CoupleExtremeTruthList.random()
        else -> "Неизвестный вопрос"
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush)) {


        Column(modifier = Modifier.fillMaxSize().background(brush).padding(top = 30.dp)
            .clickable {
                //boxVisible = !boxVisible
                navController.popBackStack(route = "choose_truth_or_dare/{type}/{modeType}", inclusive = true)
                //navigation.popBackStack(route = "DestinationC/{id}", inclusive = true)
                //navController.navigate("splash_screen_td")
        }, horizontalAlignment = Alignment.CenterHorizontally)

        {

            Box(modifier = Modifier.padding(top=30.dp)) {


                    Text(
                        modifier = Modifier,
                        text = when (typeTD) {
                            "правда" -> "правда"
                            "действие" -> "действие"
                            else -> "Неизвестный тип"
                        },
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Green, fontSize = 50.sp, fontFamily = FontFamily(
                                Font(R.font.jura_semibold)
                            )
                        )
                    )
                }


            Spacer(modifier = Modifier.height(30.dp).background(Orange))


            androidx.compose.animation.AnimatedVisibility(
                visible = boxVisible,
                enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End)
                        + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
                        + shrinkHorizontally() + fadeOut(),
            ) {

                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 40.dp, start = 10.dp, end = 10.dp)
                        .shadow(
                            elevation = 5.dp,
                            ambientColor = Color.Black,
                            spotColor = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .innerShadow(
                            shape = RoundedCornerShape(18.dp), color = Transpar,
                            offsetY = (-1).dp, offsetX = (-1).dp
                        )
                        // Top left corner shadow.
                        .innerShadow(
                            shape = RoundedCornerShape(18.dp), color = LightGray,
                            offsetY = 4.dp, offsetX = 1.dp
                        ),
                    shape = RoundedCornerShape(18.dp),
                ) {

                    Box(modifier = Modifier.align(Alignment.CenterHorizontally).background(
                        Gray), contentAlignment = Alignment.Center){
                        val text ="$playerName, $question"
                        val breakIterator = remember(text) { BreakIterator.getCharacterInstance() }

                        val typingDelayInMs = 50L

                        var substringText by remember {
                            mutableStateOf("")
                        }
                        LaunchedEffect(text) {
                            delay(500)
                            breakIterator.text = StringCharacterIterator(text)

                            var nextIndex = breakIterator.next()
                            while (nextIndex != BreakIterator.DONE) {
                                substringText = text.subSequence(0, nextIndex).toString()
                                nextIndex = breakIterator.next()
                                delay(typingDelayInMs)
                            }
                        }
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = substringText,
                            style = TextStyle(
                                color = Color.White, fontSize = 30.sp, fontFamily = FontFamily(
                                    Font(R.font.jura_semibold)
                                )
                            )
                        )
                    }
                }
            }




        }


    }

}


@Composable
fun SplashScreenTD(type: String?, modeType: String?, navController: NavController){

    val brush = Brush.linearGradient(listOf(Gray, DarkGray))
    Column(
        modifier = Modifier.background(brush).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {

        Column(
            modifier = Modifier.background(Transpar).fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.7f),
                painter = painterResource(id = R.drawable.splach_screen_stars_green_background),
                contentDescription = null,

                )
        }


        Column(
            modifier = Modifier.background(Transpar).fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(0.7f),
                painter = painterResource(id = R.drawable.splach_screen_stars_violet_background),
                contentDescription = null,

                )

        }


    }


    val scale = remember {
        Animatable(0f)
    }


    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        navController.navigate("choose_truth_or_dare/$type/$modeType")
    }



    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(Transpar)
    ) {
        // Change the logo
        Image(
            painter = painterResource(id = R.drawable.truth_or_dare_splash_screen),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value).fillMaxSize(0.6f)
        )
    }

}






