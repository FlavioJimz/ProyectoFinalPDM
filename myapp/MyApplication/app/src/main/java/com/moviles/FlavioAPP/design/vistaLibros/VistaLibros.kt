package com.moviles.FlavioAPP.design.vistaLibros

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.R
import com.moviles.FlavioAPP.design.topbar.TopBar
import com.moviles.FlavioAPP.ui.theme.cardBackground
import com.moviles.FlavioAPP.ui.theme.grayButton
import com.moviles.FlavioAPP.ui.theme.textColor

@Composable
fun VistaLibros(navController: NavController, token: String, title: String, viewModel: MainViewModel) {

    // Trigger the getBookByTitle function in the ViewModel
    LaunchedEffect(Unit) {
        viewModel.getBookByTitle(token, title)
    }

    // Observe the book detail state from the ViewModel
    val bookDetail by viewModel.bookDetail.collectAsState()

    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .height(300.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bookcover),
                    contentDescription = "Portada del libro",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Card(
                backgroundColor = cardBackground,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (bookDetail != null) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = bookDetail!!.title,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = bookDetail!!.author,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = bookDetail!!.rating.toString(),
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = bookDetail!!.description,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Cargando detalles del libro...",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* futuro */ },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = grayButton),
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Comprar",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
