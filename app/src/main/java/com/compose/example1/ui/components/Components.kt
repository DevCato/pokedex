package com.compose.example1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.example1.R
import com.compose.example1.ui.components.buttons.AddButton

@Composable
fun ShoppingCartIcon(modifier: Modifier = Modifier, onClickShoppingCart: () -> Unit) {
    Card(modifier = modifier.clickable {
        onClickShoppingCart()
    }, shape = CircleShape) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowShoppingCartIcon() {
    ShoppingCartIcon() {
    }
}

@Composable
fun CategoryChip(text: String) {
    Card(shape = RoundedCornerShape(25.dp)) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 9.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCategoryChip() {
    CategoryChip(text = "Hamburguesa")
}


@Composable
fun RestaurantCard(text: String) {
    Card(shape = RoundedCornerShape(15.dp)) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .width(IntrinsicSize.Min),
        ) {
            Image(
                modifier = Modifier.height(100.dp),
                painter = painterResource(R.drawable.kfc_sample),
                contentDescription = null
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 15.dp)
                )
                AddButton(onClick = {}, modifier = Modifier.padding(end = 15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowRestaurantCard() {
    RestaurantCard(text = "KFC")
}