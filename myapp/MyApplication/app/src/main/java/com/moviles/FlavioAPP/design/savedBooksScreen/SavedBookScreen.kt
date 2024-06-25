package com.moviles.FlavioAPP.design.savedBooksScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.R
import com.moviles.FlavioAPP.design.navbar.Navbar
import com.moviles.FlavioAPP.navigation.AppScreens
import com.moviles.FlavioAPP.ui.theme.backgroundColor
import com.moviles.FlavioAPP.ui.theme.cardBackground
import com.moviles.FlavioAPP.ui.theme.textColor

@Composable
fun SavedBooks(navController: NavController, token: String, viewModel: MainViewModel) {

    // Obtener la lista de libros desde el ViewModel
    val favoriteBooks by viewModel.favoriteBook.collectAsState()

    // Llamar a la API para obtener los libros cuando se inicializa la pantalla
    LaunchedEffect(Unit) {
        viewModel.getFavoriteBooks(token)
    }

    Scaffold(
        bottomBar = { Navbar(navController, token) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.PerfilScreen.route) },
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
                .fillMaxHeight()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(55.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LIBROS GUARDADOS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor, // Color naranja
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteBooks) { book ->
                    com.moviles.FlavioAPP.design.card.BookCard(
                        title = book.title,
                        author = book.author,
                        rating = book.rating.toDouble(),
                        imageResource = R.drawable.bookcover, // Asegúrate de que la imagen esté en tu directorio drawable
                        onCardClick = {
                            navController.navigate("${AppScreens.VistaLibroScreen.route}/${token}/${book.title}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookCard(
    title: String,
    author: String,
    rating: Double,
    imageResource: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = author,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Me gusta",
                tint = Color(0xFF00C853),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

