package com.example.truthordarejetpack

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.ShadowGreen
import com.example.truthordarejetpack.ui.theme.ShadowViolet
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.Violet
import com.google.ai.client.generativeai.type.content
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
                            .padding(20.dp),
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
                            .padding(20.dp),
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
    //r boxVisible = remember { mutableStateOf(true) }
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
                boxVisible = !boxVisible
                //navController.navigate("splash_screen")
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
                        .fillMaxWidth()
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

                    Box(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).background(
                        Gray), contentAlignment = Alignment.Center){

                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "$playerName, $question",
                            //textAlign = TextAlign.Center,
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








