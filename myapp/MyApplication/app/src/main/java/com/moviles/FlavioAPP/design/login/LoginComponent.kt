package com.moviles.FlavioAPP.design.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.moviles.FlavioAPP.MainViewModel
import com.moviles.FlavioAPP.navigation.AppScreens
import com.moviles.FlavioAPP.R
import com.moviles.FlavioAPP.ui.theme.cardBackground
import com.moviles.FlavioAPP.ui.theme.textColor

@Composable
fun LoginComponent(navController: NavController, viewModel: MainViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val loginResponse by viewModel.loginResponse


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(

                text = "Libritos",
                style = TextStyle(fontSize = 80.sp, fontFamily = FontFamily.Cursive, color = cardBackground)
            )
            Image(
                painter = painterResource(id = R.drawable.logo), // Asegúrate de que logo.png está en res/drawable
                contentDescription = "App Logo",
                modifier = (Modifier.size(150.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    showError = email.isEmpty() || password.isEmpty()
                    viewModel.login(email, password)
                    Log.d("token", loginResponse?.token.toString())
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = textColor)
            ) {
                Text(text = "Login", color = Color.White)
            }
            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Please enter both email and password",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                text = AnnotatedString("Forgot password?"),
                onClick = { /* Navegar a la pantalla de recuperación de contraseña */ },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF01204E)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                text = AnnotatedString("Sign up here"),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF01204E)
                )
            )
        }
    }

    LaunchedEffect(loginResponse) {
        loginResponse?.token?.let { token ->
            Log.d("token", token)
            navController.navigate("${AppScreens.mainViewScreen.route}/$token")
        }
    }
}


