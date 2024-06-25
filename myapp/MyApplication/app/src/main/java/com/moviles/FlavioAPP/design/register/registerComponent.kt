package com.moviles.FlavioAPP.design.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.navigation.AppScreens
import com.moviles.FlavioAPP.ui.theme.backgroundColor
import com.moviles.FlavioAPP.ui.theme.buttonColor
import com.moviles.FlavioAPP.ui.theme.formBackgroundColor
import com.moviles.FlavioAPP.ui.theme.textColor

@Composable
fun RegisterComponent(navController: NavController, viewModel: MainViewModel) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var name by remember { mutableStateOf(TextFieldValue()) }
    var surname by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    val registerResponse by viewModel.registerResponse
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(registerResponse) {
        registerResponse?.let {
            scaffoldState.snackbarHostState.showSnackbar(it.message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor,
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState,
                modifier = Modifier.padding(top = 8.dp) // Padding to prevent overlap with the status bar
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(formBackgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(textColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon") },
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Name Icon") },
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = { Text("Apellido", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Surname Icon") },
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Teléfono", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = "Phone Icon") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = textColor, fontSize = 12.sp) },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle(color = textColor, fontSize = 12.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        backgroundColor = formBackgroundColor
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Log.d("prueba", username.text.toString())
                        viewModel.register(
                            username.text.toString(),
                            name.text.toString(), surname.text.toString(),
                            email.text.toString(), phone.text.toString(), password.text.toString()
                        )
                        navController.navigate(AppScreens.MainScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = textColor),
                    shape = RoundedCornerShape(60.dp)
                ) {
                    Text(text = "Crear", color = Color.White)
                }
                Spacer(modifier = Modifier.height(41.dp))

                ClickableText(
                    text = AnnotatedString("¿Ya tienes un usuario creado?"),
                    onClick = { navController.navigate("first_screen") },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.Underline,
                        color = textColor
                    )
                )
            }
        }
    }
}

