package com.compose.example1.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.compose.example1.R
import com.compose.example1.ui.components.CategoryChip
import com.compose.example1.ui.components.RestaurantCard
import com.compose.example1.ui.components.ShoppingCartIcon
import com.compose.example1.ui.theme.JetpackComposeDarkThemeTheme

@Composable
fun DashboardFragment(onClickShoppingCart: () -> Unit) {
    JetpackComposeDarkThemeTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(onClickShoppingCart)
        }
    }
}

@Composable
fun MainScreen(onClickShoppingCart: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium_20dp))
            .verticalScroll(rememberScrollState())
    ) {
        MainHeader(onClickShoppingCart)
        Spacer(modifier = Modifier.size(30.dp))
        CategoriesSection()
        Spacer(modifier = Modifier.size(30.dp))
        MainSection()
        Spacer(modifier = Modifier.size(30.dp))
        MainSection()
        Spacer(modifier = Modifier.size(30.dp))
        MainSection()
        Spacer(modifier = Modifier.size(30.dp))
        MainSection()
        Spacer(modifier = Modifier.size(30.dp))
        MainSection()
    }
}

@Composable
fun MainHeader(onClickShoppingCart: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (image) = createRefs()

        Column {
            Text(text = "Bienvenido")
            Spacer(Modifier.size(dimensionResource(id = R.dimen.padding_medium_6dp)))
            Text(text = "Carlos Arizola", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        ShoppingCartIcon(modifier = Modifier.constrainAs(image) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            onClickShoppingCart()
        }
    }
}

@Composable
fun CategoriesSection() {
    val categoryArray = listOf("Pizza", "Hamburguesa", "Bebidas", "Pollo", "Pescados", "Carne")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Categorias Populares", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Ver todas",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(categoryArray.size) {
                    CategoryChip(text = categoryArray[it])
                }
            })
    }
}

@Composable
fun MainSection() {
    val categoryArray = listOf("Pizza", "Hamburguesa", "Bebidas", "Pollo", "Pescados", "Carne")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Restaurantes cerca de ti", fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(categoryArray.size) {
                    RestaurantCard(text = categoryArray[it])
                }
            }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    JetpackComposeDarkThemeTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(){}
        }
    }
}