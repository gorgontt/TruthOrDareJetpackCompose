package com.example.truthordarejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.Purple40


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChooseVersion()
        }
    }
}

@Preview
@Composable
fun ChooseVersion() {

    Box(modifier = Modifier.background(color = Gray).fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {

            Text(
                modifier = Modifier.offset(28.dp, 28.dp).fillMaxHeight(0.15f).fillMaxWidth(0.7f),
                text = "Выберите версию игры",
                style = TextStyle(color = Color.White, fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.juraa)))
            )




            Card(modifier = Modifier
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

                },
                shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

            ) {

                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = Gray)){
                    Text(
                        text = "Пара",
                        style = TextStyle(color = Green, fontSize = 50.sp, fontFamily = FontFamily(Font(R.font.juraa))))
                }


            }





            Card(modifier = Modifier
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

                },
                shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

                ) {

                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = Gray)){
                    Text(
                        text = "Компания",
                        style = TextStyle(color = Green, fontSize = 50.sp, fontFamily = FontFamily(Font(R.font.juraa))))
                }


            }




        }



    }

    
}

