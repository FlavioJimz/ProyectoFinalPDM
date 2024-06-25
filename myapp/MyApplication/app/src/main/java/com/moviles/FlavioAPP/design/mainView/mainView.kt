package com.moviles.FlavioAPP.design.mainView

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.R
import com.moviles.FlavioAPP.design.card.BookCard
import com.moviles.FlavioAPP.design.navbar.Navbar
import com.moviles.FlavioAPP.model.Book
import com.moviles.FlavioAPP.model.User
import com.moviles.FlavioAPP.navigation.AppScreens
import com.moviles.FlavioAPP.ui.theme.backgroundColor
import com.moviles.FlavioAPP.ui.theme.cardBackground
import com.moviles.FlavioAPP.ui.theme.grayButton
import com.moviles.FlavioAPP.ui.theme.likeButton
import com.moviles.FlavioAPP.ui.theme.textColor

@Composable
fun BookScreen(navController: NavController, token: String, viewModel: MainViewModel) {
    Log.d("token en el mainview", token)

    // Estado para el índice del libro actual
    var currentBookIndex by remember { mutableStateOf(0) }

    // Obtener la lista de libros desde el ViewModel
    val books by viewModel.books.collectAsState()

    val user by viewModel.userInfo.collectAsState()

    // Llamar a la API para obtener los libros cuando se inicializa la pantalla
    LaunchedEffect(Unit) {
        viewModel.getAllBooks(token)
        viewModel.getUserInfo(token)
    }

    Scaffold(
        bottomBar = { Navbar(navController, token) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreens.PerfilScreen.route)  },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)), // Recortar la columna con esquinas redondeadas
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Descubre",
                    style = TextStyle(fontSize = 100.sp, fontFamily = FontFamily.Cursive, color = Color(0xFF01204E)),
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Color naranja
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .clip(RoundedCornerShape(25.dp)) // Recortar el texto con esquinas redondeadas
                )

                // Mostrar la tarjeta del libro solo si la lista de libros no es nula y no está vacía
                if (books.isNotEmpty()) {
                    val book = books[currentBookIndex % books.size]
                    BookCard(
                        title = book.title,
                        author = book.author,
                        rating = book.rating.toDouble(),
                        imageResource = R.drawable.bookcover, // Asegúrate de que la imagen esté en tu directorio drawable
                        onCardClick = {
                            navController.navigate("${AppScreens.VistaLibroScreen.route}/${token}/${book.title}")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(
                            4.dp,
                            RoundedCornerShape(16.dp)
                        ) // Añadir sombra a la fila con esquinas redondeadas
                        .clip(RoundedCornerShape(16.dp)), // Recortar la fila con esquinas redondeadas
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularButton(
                        icon = Icons.Filled.Close,
                        onClick = { currentBookIndex = (currentBookIndex + 1) % books.size },
                        backgroundColor = Color.Red
                    )
                    CircularButton2(
                        icon = Icons.Filled.ShoppingCart,
                        onClick = { /* Acción para comprar */ },
                        backgroundColor = Color.Black
                    )
                    CircularButtonPass(
                        icon = Icons.Filled.Favorite,
                        onClick = {
                            currentBookIndex = (currentBookIndex + 1) % books.size

                            val book = books[currentBookIndex % books.size]
                            user?.let {
                                viewModel.favoriteBook(token, it, book)
                            } ?: Log.d("error", "User information is not available")
                        },
                        backgroundColor = Color.Blue
                    )
                }
            }
        }
    }
}

@Composable
fun CircularButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .shadow(4.dp, CircleShape) // Añadir sombra al botón circular
            .clip(CircleShape) // Recortar el botón en forma circular
            .background(color = grayButton, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun CircularButton2(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .shadow(4.dp, CircleShape) // Añadir sombra al botón circular
            .clip(CircleShape) // Recortar el botón en forma circular
            .background(color = grayButton, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun CircularButtonPass(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .shadow(4.dp, CircleShape) // Añadir sombra al botón circular
            .clip(CircleShape) // Recortar el botón en forma circular
            .background(color = grayButton, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = likeButton,
            modifier = Modifier.size(30.dp)
        )
    }
}

