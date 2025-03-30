package com.example.truthordarejetpack

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.truthordarejetpack.ui.theme.ShadowGreen
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.TruthOrDareJetpackTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TruthOrDareJetpackTheme {
                val brush = Brush.linearGradient(listOf(Gray, DarkGray))
                Surface(modifier = Modifier.fillMaxSize().background(brush)) {
                    Navigation()
                }

            }

        }
    }


    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        val context = LocalContext.current
        val savedPlayers = loadPlayers(context).toMutableList()
        val playersList = remember { mutableStateListOf(*savedPlayers.toTypedArray()) }

        val onDismiss: () -> Unit = { /* действие при закрытии */ }

        NavHost(
            navController = navController,
            startDestination = "splash_screen"
        ) {
            composable(Routes.SplashScreen.route) { SplashScreen(navController) }
            composable(Routes.ChooseVersion.route) { ChooseVersion(navController) }

            // 1 навигация
            composable("players_list/{type}") { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                type?.let {
                    AddNewPlayers(type = it, navController, playersList)
                }
            }

            composable("pager/{type}") { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                type?.let {
                    Pager(type = it, playersList, onDismiss, navController)
                }
            }

            composable("choose_truth_or_dare/{type}/{modeType}") { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                val modeType = backStackEntry.arguments?.getString("modeType")

                ChooseTruthOrDare(type = type, modeType = modeType, playersList, onDismiss, navController)

            }

            // Question Screen
            composable("question/{type}/{modeType}/{typeTD}") { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                val typeTD = backStackEntry.arguments?.getString("typeTD")
                val modeType = backStackEntry.arguments?.getString("modeType")

                Question(type = type, modeType = modeType, typeTD = typeTD, navController = navController)
            }
        }
    }



    @Composable
    fun SplashScreen(navController: NavController) {

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
                // tween Animation
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            // Customize the delay time
            delay(1000L)
            navController.navigate("main_screen")
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


    @Composable
    fun ChooseVersion(navController: NavController) {
        val brush = Brush.linearGradient(listOf(Gray, DarkGray))
        Box(
            modifier = Modifier
                .fillMaxSize().background(brush)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f).background(brush),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier.offset(28.dp, 28.dp)
                        .fillMaxHeight(0.15f)
                        .fillMaxWidth(0.7f),
                    text = "Выберите версию игры",
                    style = TextStyle(
                        color = Color.White, fontSize = 32.sp, fontFamily = FontFamily(
                            Font(R.font.juraa)
                        )
                    )
                )

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
                            navController.navigate("players_list/пара")



                        },
                    shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize().background(brush)
                    ) {

                        Image(
                            modifier = Modifier.align(Alignment.TopEnd).padding(20.dp),
                            painter = painterResource(id = R.drawable.couple_icon),
                            contentDescription = "CoupleIcon"
                        )

                        Text(
                            text = "Пара",
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
                            color = Green,
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
                            shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp), color = ShadowGreen,
                            offsetY = (-8).dp, offsetX = (-8).dp
                        )
                        // Top left corner shadow.
                        .innerShadow(
                            shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp), color = ShadowGreen,
                            offsetY = 8.dp, offsetX = 8.dp
                        )
                        .clickable {
                            navController.navigate("players_list/компания")
                        },
                    shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize().background(brush)
                    ) {

                        Image(
                            modifier = Modifier.align(Alignment.TopStart).padding(20.dp),
                            painter = painterResource(id = R.drawable.company_icon),
                            contentDescription = "CompanyIcon"
                        )

                        Text(
                            text = "Компания",
                            style = TextStyle(
                                color = Green, fontSize = 50.sp, fontFamily = FontFamily(
                                    Font(R.font.juraa)
                                )
                            )
                        )
                    }
                }
            }
        }
    }


}






