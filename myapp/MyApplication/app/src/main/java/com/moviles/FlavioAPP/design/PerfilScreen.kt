package com.moviles.FlavioAPP.design.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.design.navbar.Navbar
import com.moviles.FlavioAPP.ui.theme.cardBackground
import com.moviles.FlavioAPP.ui.theme.grayButton

@Composable
fun ProfileScreen(navController: NavController, token : String) {
    Scaffold(
        bottomBar = { Navbar(navController, token) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                backgroundColor = cardBackground,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Centro"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) { paddingValues ->
        Column(
            modifier = Modifier

                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cardBackground)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFF5863F8), shape = CircleShape)
                            .padding(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Flavio Jiménez",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Hey, estas usando libritos",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileField("Flavio Jimenez")
                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileField("Flaviojimenez@gmail.com")
                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileField("75896325")
                }
            }
        }
    }
}

@Composable
fun ProfileField(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .background( grayButton, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = label, color = Color.Black, fontSize = 16.sp)
    }
}

