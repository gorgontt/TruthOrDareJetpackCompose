package com.example.truthordarejetpack

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarejetpack.ui.theme.DarkGray
import com.example.truthordarejetpack.ui.theme.DarkOrange
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.LightOrange
import com.example.truthordarejetpack.ui.theme.Orange
import com.example.truthordarejetpack.ui.theme.ShadowGreen
import com.example.truthordarejetpack.ui.theme.Transpar
import com.example.truthordarejetpack.ui.theme.Violet


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddNewPlayers(type: String?) {

    val context = LocalContext.current // Получаем контекст
    val brush = Brush.linearGradient(listOf(Gray, DarkGray))

    val savedPlayers = loadPlayers(context) // Передаем контекст
    val playersList = remember { mutableStateListOf(*savedPlayers.toTypedArray()) }



    Scaffold(
        modifier = Modifier.background(brush),

        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title= { Text(
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

            )},
                navigationIcon={ IconButton({ }) { Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "Меню")}},

                colors= TopAppBarDefaults.topAppBarColors(containerColor = Gray,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.LightGray))
        },

        bottomBar = {
            BottomAppBar(
                containerColor = Transpar,
                contentColor = Transpar
            ){
                BottomBar(playersList)
            }
        }, content = {
            PlayersList(playersList) { playerName ->
                playersList.remove(playerName) // Удаляем игрока из списка
                savePlayers(context, playersList) // Сохраняем изменения в SharedPreferences
            }
        }
    )
}

private fun savePlayers(context: Context, playersList: List<String>) {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putStringSet("players", playersList.toSet())
        apply()
    }
}

@Composable
fun BottomSheetDialogContent(playersList: MutableList<String>) {

    var text by remember { mutableStateOf("") }

    val context = LocalContext.current // Получаем контекст

    Column(
        modifier = Modifier.fillMaxSize(),

    ) {

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            text = "Имя игрока",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.juraa))
            )
        )

        Spacer(modifier = Modifier.height(15.dp))


        OutlinedTextField(

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            value = text,
            onValueChange = { text = it },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),

            textStyle = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.juraa))
            ),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Violet,
                unfocusedBorderColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp)

        )


        Column(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {

            Card(

                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        ambientColor = Violet,
                        spotColor = Violet,
                        shape = CircleShape
                    )
                    .background(Gray)


            ) {

                IconButton(
                    modifier = Modifier.size(60.dp),
                    colors = IconButtonDefaults.iconButtonColors(Violet),
                    onClick = {
                        if (text.isNotBlank()) {
                            playersList.add(text) // Добавляем нового игрока
                            text = "" // Очищаем TextField
                            savePlayers(context, playersList = playersList) // Сохраняем изменения в SharedPreferences
                        }
                    }
                )
                {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.7f),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = Green
                    )
                }

            }
        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(playersList: MutableList<String>) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 20.dp).background(
            Transpar),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(

            modifier = Modifier
                .background(Transpar)
                .shadow(
                    elevation = 5.dp,
                    ambientColor = Color.Black,
                    spotColor = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )


        )

        {

            Surface(
                modifier = Modifier.background(DarkGray)

            ) {
                val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
                var isSheetOpen by rememberSaveable {
                    mutableStateOf(false)
                }



                Button(
                    onClick = {
                        isSheetOpen = true

                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .size(height = 70.dp, width = 60.dp )
                        .background(Gray)
                        .innerShadow(
                            shape = RoundedCornerShape(15.dp), color = Color.Black,
                            offsetY = (-0).dp, offsetX = (-0).dp
                        )
                        // Top left corner shadow.
                        .innerShadow(
                            shape = RoundedCornerShape(15.dp), color = Color.LightGray,
                            offsetY = 2.dp, offsetX = 0.dp
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Gray),
                    contentPadding = PaddingValues(1.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        tint = Green,
                        contentDescription = "Add",
                        modifier = Modifier.size(30.dp)
                    )
                }

                if (isSheetOpen) {
                    ModalBottomSheet(
                        containerColor = Gray,
                        sheetState = sheetState,
                        onDismissRequest = { isSheetOpen = false }
                    ) {
                        BottomSheetDialogContent(playersList) // Передача playersList
                    }
                }


            }


        }

        Spacer(modifier = Modifier.width(8.dp))

        Card (
            modifier = Modifier
                .size(height = 70.dp, width = 500.dp)
                .padding(0.dp)
                .background(Transpar)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = LightOrange,
                    spotColor = LightOrange,
                    shape = RoundedCornerShape(15.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(15.dp), color = Color.Black,
                    offsetY = (-0).dp, offsetX = (-0).dp
                )
                // Top left corner shadow.
                .innerShadow(
                    shape = RoundedCornerShape(15.dp), color = Color.LightGray,
                    offsetY = 2.dp, offsetX = 0.dp
                ),
            shape = RoundedCornerShape(15.dp),
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray)
            ){

                Button(
                    modifier = Modifier
                        .fillMaxHeight(0.85f).fillMaxWidth(0.95f)
                        .innerShadow(
                            shape = RoundedCornerShape(15.dp), color = DarkOrange,
                            offsetY = (-4).dp, offsetX = (-4).dp
                        )
                        // Top left corner shadow.
                        .innerShadow(
                            shape = RoundedCornerShape(15.dp), color = LightOrange,
                            offsetY = 4.dp, offsetX = 4.dp
                        ),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    onClick = {}
                )
                {

                    Text(
                        "Начать",
                        modifier = Modifier.background(Transpar),
                        style = TextStyle(
                            color = DarkGray,
                            fontSize = 26.sp,
                            fontFamily = FontFamily(Font(R.font.jura_semibold))
                        )
                    )


                }

            }



        }

    }




}


@Composable
fun PlayersList(playersList: MutableList<String>, onPlayerDelete: (String) -> Unit) {
    val brush = Brush.linearGradient(listOf(Gray, DarkGray))
    LazyColumn(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(brush).offset(0.dp, 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = playersList) { playerName ->


            Card(
                modifier = Modifier.fillMaxWidth(0.8f).padding(start = 0.dp, end = 0.dp, top = 15.dp)
                    .border(
                        width = 2.dp,
                        color = Green,
                        shape = RoundedCornerShape(37.dp)

                    )
                    .background(Transpar)
                    .shadow(
                        elevation = 10.dp,
                        ambientColor = Color.Black,
                        spotColor = Color.Black,
                        shape = RoundedCornerShape(37.dp)
                    )

                    .innerShadow(
                        shape = RoundedCornerShape(40.dp, 50.dp, 37.dp, 50.dp), color = ShadowGreen,
                        offsetY = (-8).dp, offsetX = (-6).dp
                    )

                    // Top left corner shadow.
                    .innerShadow(
                        shape = RoundedCornerShape(40.dp, 50.dp, 37.dp, 50.dp), color = ShadowGreen,
                        offsetY = 8.dp, offsetX = 6.dp
                    ),
                shape = RoundedCornerShape(37.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().background(Gray)
                ) {

                    Icon(
                        modifier = Modifier.align(Alignment.CenterEnd).offset(-20.dp, 0.dp).clickable {
                            onPlayerDelete(playerName)
                        },
                        painter = painterResource(R.drawable.delete_icon),
                        contentDescription = "Delete",
                        tint = Green
                    )

                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = playerName,
                        style = TextStyle(
                            color = Green, fontSize = 28.sp, fontFamily = FontFamily(
                                Font(R.font.jura_semibold)
                            )
                        )
                    )
                }

            }
        }
    }
}




private fun loadPlayers(context: Context): Set<String> {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet("players", emptySet()) ?: emptySet()
}





            




