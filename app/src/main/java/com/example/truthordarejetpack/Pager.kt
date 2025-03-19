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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarejetpack.ui.theme.DarkGray
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.LightGray
import com.example.truthordarejetpack.ui.theme.OnBoardingData
import com.example.truthordarejetpack.ui.theme.Orange
import com.example.truthordarejetpack.ui.theme.Pink
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.Transpar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Pager(onDismiss: () -> Unit){

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
                item = items,
                pagerState = pagerState,
                colors = colors,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
    modifier: Modifier = Modifier
){

    Box (
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ){
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) {page->

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Column(
                        modifier = Modifier.background(Transpar).fillMaxWidth(),
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
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Image(
                            alignment = Alignment.Center,
                            painter = painterResource(id = item[page].card), contentDescription = ""
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
                                .innerShadow(
                                    shape = RoundedCornerShape(18.dp), color = DarkGray,
                                    offsetY = (-4).dp, offsetX = (-4).dp
                                )
                                // Top left corner shadow.
                                .innerShadow(
                                    shape = RoundedCornerShape(18.dp), color = LightGray,
                                    offsetY = 4.dp, offsetX = 4.dp
                                ),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Gray),
                            onClick = {}
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
                        modifier = Modifier.background(Transpar).fillMaxWidth(),
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

