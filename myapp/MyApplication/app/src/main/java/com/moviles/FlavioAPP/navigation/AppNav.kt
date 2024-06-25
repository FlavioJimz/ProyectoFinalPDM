package com.moviles.FlavioAPP.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.design.login.LoginComponent
import com.moviles.FlavioAPP.design.mainView.BookScreen
import com.moviles.FlavioAPP.design.profileScreen.ProfileScreen
import com.moviles.FlavioAPP.design.register.RegisterScreen
import com.moviles.FlavioAPP.design.savedBooksScreen.SavedBooks
import com.moviles.FlavioAPP.design.vistaLibros.VistaLibros

@Composable
fun AppNav(viewModel: MainViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route){
            LoginComponent(navController, viewModel)
    }

        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen(navController, viewModel)
        }

        composable(
            route = "${AppScreens.mainViewScreen.route}/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            BookScreen(navController, token, viewModel)
        }

        composable(
            route = "${AppScreens.SavedBooksScreen.route}/{token}",
            arguments = listOf(navArgument("token") { type = NavType.StringType })
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            SavedBooks(navController, token, viewModel)
        }

        composable(
            route = AppScreens.PerfilScreen.route
        ){
            ProfileScreen(navController, token = "example")
        }



        composable(
            route = "${AppScreens.VistaLibroScreen.route}/{token}/{title}",
            arguments = listOf(
                navArgument("token") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            VistaLibros(navController, token, title, viewModel)
        }
    }
}