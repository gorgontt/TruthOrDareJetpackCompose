package com.example.truthordarejetpack

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.truthordarejetpack.ui.theme.DarkGray
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.LightGray
import com.example.truthordarejetpack.ui.theme.OnBoardingData
import com.example.truthordarejetpack.ui.theme.Orange
import com.example.truthordarejetpack.ui.theme.Pink
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.ShadowGreen
import com.example.truthordarejetpack.ui.theme.ShadowViolet
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.Violet
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Pager(playersList: List<String>, onDismiss: () -> Unit){

    val text1 = "Soft"
    val text2 = "Hot"
    val text3 = "Hard"
    val text4 = "Extreme"

    val btnText = "Начать"

    val colors = listOf(Green, Orange, Red, Pink)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray)
    ) {
        val items = ArrayList<OnBoardingData>()
        items.add(
            OnBoardingData(
                R.drawable.suit_green,
                R.drawable.splach_screen_stars_green_background,
                R.drawable.stars_green2_background,
                text1,
                btnText

            )
        )

        items.add(
            OnBoardingData(
                R.drawable.suit_orange,
                R.drawable.stars_orange_background,
                R.drawable.stars_orange2_background,
                text2,
                btnText
            )
        )

        items.add(
            OnBoardingData(
                R.drawable.suit_red,
                R.drawable.stars_red_background,
                R.drawable.stars_red1_background,
                text3,
                btnText
            )
        )

        items.add(
            OnBoardingData(
                R.drawable.suit_pink,
                R.drawable.stars_pink_background,
                R.drawable.stars_pink1_background,
                text4,
                btnText
            )
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Gray),
            contentAlignment = Alignment.Center){

            val pagerState = com.google.accompanist.pager.rememberPagerState(
                pageCount = items.size,
                initialOffscreenLimit = 2,
                infiniteLoop = false,
                initialPage = 0
            )

            OnBoardingPager(
                //navController = NavController(context = Context),
                item = items,
                pagerState = pagerState,
                colors = colors,
                playersList = playersList,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )

        }

    }

}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(

    item: List<OnBoardingData>,
    pagerState: PagerState,
    colors: List<Color>,
    playersList: List<String>,
    modifier: Modifier = Modifier
){

    val brush = Brush.linearGradient(listOf(Gray, DarkGray))

    var showTruthOrDare by remember { mutableStateOf(false) }

    Box (
        modifier = Modifier
            .background(brush)
            .fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->

                if (showTruthOrDare) {
                    ChooseTruthOrDare(playersList) {
                        showTruthOrDare = false
                    } // передаем список игроков
                } else {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier
                                .background(Transpar)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                painter = painterResource(id = item[page].background1),
                                contentDescription = null,

                                )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Image(
                                alignment = Alignment.Center,
                                painter = painterResource(id = item[page].card),
                                contentDescription = ""
                            )

                            Text(
                                text = item[page].title,
                                style = TextStyle(
                                    color = colors[page],
                                    fontSize = 50.sp,
                                    fontFamily = FontFamily(Font(R.font.juraa))
                                )
                            )

                            Spacer(modifier = Modifier.height(20.dp))


                            Button(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(0.7f)
                                    .padding(0.dp)
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
                                colors = ButtonDefaults.buttonColors(containerColor = Gray),
                                onClick = { showTruthOrDare = true } //navController.navigate("players_list/пара")
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    text = item[page].btn,
                                    style = TextStyle(
                                        color = colors[page],
                                        fontSize = 36.sp,
                                        fontFamily = FontFamily(Font(R.font.jura_semibold))
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(50.dp))

                        }

                        Column(
                            modifier = Modifier
                                .background(Transpar)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {

                            Image(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                painter = painterResource(id = item[page].background2),
                                contentDescription = null,

                                )

                        }

                    }


                }
            }
        }

    }
}




@Composable
fun ChooseTruthOrDare(playersList: List<String>, onDismiss: () -> Unit){

    val brush = Brush.linearGradient(listOf(Gray, DarkGray))

    // Выбираем случайное имя игрока
    val randomPlayerName = if (playersList.isNotEmpty()) {
        playersList[Random.nextInt(playersList.size)]
    } else {
        "Нет игроков" // Обработка случая, когда список игроков пуст
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
//                horizontalArrangement = Arrangement.Center
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
                        //navController.navigate("players_list/пара")
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
                        //navController.navigate("players_list/компания")
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


