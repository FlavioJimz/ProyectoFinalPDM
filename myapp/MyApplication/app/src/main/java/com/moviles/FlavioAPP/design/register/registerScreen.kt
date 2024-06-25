package com.moviles.FlavioAPP.design.register

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.moviles.FlavioAPP.MainViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: MainViewModel){
    RegisterComponent(navController, viewModel)
}
