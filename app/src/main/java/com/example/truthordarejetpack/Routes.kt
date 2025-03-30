package com.example.truthordarejetpack

sealed class Routes(val route: String) {
    object SplashScreen : Routes("splash_screen")
    object ChooseVersion : Routes("main_screen")
    object Pager : Routes("pager/{type}") // Добавлено {type}
    object ChooseTruthorDare : Routes("truth_or_dare/{type}") // Добавлено {type}
}

