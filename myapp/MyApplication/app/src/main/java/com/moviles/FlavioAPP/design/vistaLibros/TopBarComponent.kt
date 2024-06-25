package com.moviles.FlavioAPP.design.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviles.FlavioAPP.R // Asegúrate de importar el paquete correcto para los recursos
import com.moviles.FlavioAPP.ui.theme.cardBackground

@Composable
fun TopBar(navController: NavController? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(cardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Books",
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}
