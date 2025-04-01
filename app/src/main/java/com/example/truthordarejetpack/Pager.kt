package com.example.truthordarejetpack

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.Violet
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Pager(type: String, playersList: List<String>, onDismiss: () -> Unit, navController: NavController){

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
                type,
                item = items,
                pagerState = pagerState,
                colors = colors,
                playersList = playersList,
                navController = navController,
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

    type: String,
    item: List<OnBoardingData>,
    pagerState: PagerState,
    colors: List<Color>,
    playersList: List<String>,
    navController: NavController,
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
//                                modifier = Modifier
//                                    .animatedBorder(
//                                    borderColors = listOf(Color.Red, Color.Green, Color.Blue),
//                                    backgroundColor = Transpar,
//                                    shape = RoundedCornerShape(16.dp),
//                                    borderWidth = 4.dp
//                                ),
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
                                onClick = {
                                    if (page == 0) {
                                        navController.navigate("splash_screen_td/$type/soft")
                                    }

                                    if (page == 1) {
                                        navController.navigate("splash_screen_td/$type/hot")
                                    }

                                    if (page == 2) {
                                        navController.navigate("splash_screen_td/$type/hard")
                                    }

                                    if (page == 3) {
                                        navController.navigate("splash_screen_td/$type/extreme")
                                    }

                                }
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






