package com.moviles.FlavioAPP.design.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.navigation.AppScreens
import com.moviles.FlavioAPP.ui.theme.cardBackground

@Composable
fun Navbar(navController: NavController, token: String) {
    BottomAppBar(
        backgroundColor = cardBackground,
        cutoutShape = CircleShape


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate(AppScreens.MainScreen.route) }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Libros",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate("${AppScreens.SavedBooksScreen.route}/$token") }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

