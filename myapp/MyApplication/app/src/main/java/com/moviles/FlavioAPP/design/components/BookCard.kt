package com.moviles.FlavioAPP.design.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moviles.FlavioAPP.R
import com.moviles.FlavioAPP.ui.theme.backgroundColor
import com.moviles.FlavioAPP.ui.theme.cardBackground

@Composable
fun BookCard(
    title: String,
    author: String,
    rating: Double,
    imageResource: Int,
    onCardClick: (() -> Unit)? = null
) {
    Card(
        backgroundColor = cardBackground,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 400.dp)
            .clickable { onCardClick?.invoke() }
    ) {
        Column {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = backgroundColor
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = author,
                            style = MaterialTheme.typography.body2,
                            color = backgroundColor
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = rating.toString(),
                                style = MaterialTheme.typography.body2,
                                color = backgroundColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    BookCard(
        title = "Twilight",
        author = "Stephenie Meyer",
        rating = 4.7,
        imageResource = R.drawable.bookcover
    )
}
