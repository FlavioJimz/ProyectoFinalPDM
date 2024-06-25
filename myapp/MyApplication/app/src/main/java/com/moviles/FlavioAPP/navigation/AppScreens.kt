package com.moviles.FlavioAPP.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("first_screen")
    object  RegisterScreen : AppScreens("RegiterScreen")
    object VistaLibroScreen : AppScreens("VistaLibroScreen")
    object  mainViewScreen : AppScreens("MainViewScreen")
    object SavedBooksScreen : AppScreens("SavedBooks")
    object PerfilScreen : AppScreens("PerfilScreen")
}