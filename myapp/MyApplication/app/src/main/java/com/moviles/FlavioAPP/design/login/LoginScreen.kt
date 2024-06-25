package com.moviles.FlavioAPP.design.login

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.moviles.FlavioAPP.MainViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: MainViewModel){

    Column {

        LoginComponent(navController, viewModel)
    }

}