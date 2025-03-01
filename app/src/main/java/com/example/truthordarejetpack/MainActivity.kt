package com.example.truthordarejetpack

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.TruthOrDareJetpackTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(color = Gray, modifier = Modifier.fillMaxSize()) {
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "splash_screen"
        ) {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }

            // Main Screen
            composable("main_screen") {
                ChooseVersion(navController)
            }

            // Players List Screen
            composable("players_list/{type}") { backStackEntry ->
                val type = backStackEntry.arguments?.getString("type")
                AddNewPlayers(type)
            }
        }
    }

    @Composable
    fun SplashScreen(navController: NavController) {
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

        // Image
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Change the logo
            Image(
                painter = painterResource(id = R.drawable.truth_or_dare_splash_screen),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    }


    @Composable
    fun ChooseVersion(navController: NavController) {
        Box(modifier = Modifier
            .background(color = Gray)
            .fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f),
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
                        .background(color = Gray)
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.4f)
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = Green,
                            spotColor = Green,
                            shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp)
                        )
                        .clickable {
                            navController.navigate("players_list/пара")
                        },
                    shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize().background(color = Gray)
                    ) {
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
                        .background(color = Gray)
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.7f)
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = Green,
                            spotColor = Green,
                            shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp)
                        )
                        .clickable {
                            navController.navigate("players_list/компания")
                        },
                    shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize().background(color = Gray)
                    ) {
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


