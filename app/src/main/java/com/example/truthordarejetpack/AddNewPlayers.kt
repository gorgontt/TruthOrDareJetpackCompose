package com.example.truthordarejetpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarejetpack.ui.theme.Gray
import com.example.truthordarejetpack.ui.theme.Green
import com.example.truthordarejetpack.ui.theme.Red
import com.example.truthordarejetpack.ui.theme.Violet
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
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

    Column(
        modifier = Modifier.fillMaxHeight(0.9f).fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )


    {

        Column(
            horizontalAlignment = Alignment.End


        ) {
            Card(

                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        ambientColor = Green,
                        spotColor = Green,
                        shape = RoundedCornerShape(10.dp)
                    )

            )

            {

                Surface(
                    modifier = Modifier.background(Green)
                ) {
                    val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
                    var isSheetOpen by rememberSaveable {
                        mutableStateOf(false)
                    }

                        Button(
                            onClick = {
                                isSheetOpen = true

                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.size(60.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Green),
                            contentPadding = PaddingValues(1.dp)
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.add),
                                tint = Color.Black,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(30.dp)
                            )
                        }

                    if (isSheetOpen){
                        ModalBottomSheet(
                            containerColor = Gray,
                            sheetState = sheetState,
                            onDismissRequest = {isSheetOpen = false}
                        ) {

                            BottomSheetDialogContent()

                        }

                    }


                }



            }



            Spacer(modifier = Modifier.height(20.dp))



            Card(

                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        ambientColor = Violet,
                        spotColor = Violet,
                        shape = RoundedCornerShape(10.dp)
                    )


            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Violet),
                    onClick = {}
                )
                {
                    Text(
                        "Начать",
                        style = TextStyle(
                            color = Green,
                            fontSize = 35.sp,
                            fontFamily = FontFamily(Font(R.font.jura_semibold))
                        )
                    )
                }

            }


        }
    }

}



@Composable
fun BottomSheetDialogContent(){

    var text by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        //horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            text = "Имя игрока",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.juraa))
            )
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(

            modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp),
            value = text,
            onValueChange = {text = it},
            leadingIcon = { Icon(imageVector = Icons.Default.Person,contentDescription = "", tint = Color.White) },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
//            label = { Text("Игрок", style = TextStyle(color = Color.White,
//                fontSize = 16.sp,
//                fontFamily = FontFamily(Font(R.font.juraa)))) },
            textStyle = TextStyle(color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.juraa))),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Violet,
                unfocusedBorderColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp)

        )

//        Spacer(modifier = Modifier.height(100.dp))

        Column(

            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f).padding(start = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Card(

                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        ambientColor = Violet,
                        spotColor = Violet,
                        shape = RoundedCornerShape(10.dp)
                    )


            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Violet),
                    onClick = {}
                )
                {
                    Text(
                        "Добавить",
                        style = TextStyle(
                            color = Green,
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.jura_semibold))
                        )
                    )
                }

            }
        }




    }

}






            




