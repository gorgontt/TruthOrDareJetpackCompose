package com.example.truthordarejetpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.Violet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPlayers(type: String?) {


            CenterAlignedTopAppBar(
               modifier = Modifier.offset(20.dp, 0.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Gray),
                title = {
                    Text(
                        text = when (type) {
                            "пара" -> "Версия пара"
                            "компания" -> "Версия компания"
                            else -> "Неизвестный тип"
                        },
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.juraa))
                        )

                    )
                },

                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Arrow back"
                    )
                }
            )

    Column (
        modifier = Modifier.fillMaxHeight(0.9f).fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )


    {

        Column(
            horizontalAlignment = Alignment.End


        ) {
            Button(
                modifier = Modifier.size(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.add),
                    tint = Color.Black,
                    contentDescription = "add icon"

                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(0.9f).padding(top = 20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Violet),
                onClick = {}
            )
            {
                Text("Начать",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 40.sp,
                        fontFamily = FontFamily(Font(R.font.jura_semibold))
                    ) )
            }

        }
    }

        }





            




