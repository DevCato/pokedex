package com.example.pokedex.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.presentation.navigation.Navigation
import com.example.pokedex.ui.components.cards.PokemonItem
import com.example.pokedex.ui.components.common.TextFieldCustom
import com.example.pokedex.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.collectAsState()
            MyApplicationTheme {
                Navigation(
                    mainState = state,
                    onClickPokemon = viewModel::onClickPokemon,
                    selectedPokemon = state.value.selectedPokemon,
                    onSearchPokemon = viewModel::onSearchPokemon
                )
            }
        }
    }
}

@Composable
fun PrincipalFragment(
    mainState: State<MainState>,
    onClickPokemon: (Pokemon) -> Unit,
    onSearchPokemon: (String) -> Unit,
    goToDetailPokemonFragment: () -> Unit
) {
    val density = LocalDensity.current
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = mainState.value.isLoading.not(),
            enter = slideInVertically(
                animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
            ) {
                with(density) { 40.dp.roundToPx() }
            } + fadeIn(
                initialAlpha = 0.3f,
                animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
            )) {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(20.dp),
                columns = GridCells.Fixed(2)
            ) {
                item(span = { GridItemSpan(this.maxLineSpan) }) {
                    TextFieldCustom(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        trailingIcon = Icons.Filled.Search
                    ){
                        onSearchPokemon(it.text)
                    }
                }
                items(mainState.value.searchPokemonList.size) {
                    PokemonItem(
                        pokemon = mainState.value.searchPokemonList[it],
                        modifier = Modifier.clickable {
                            onClickPokemon(mainState.value.searchPokemonList[it])
                            goToDetailPokemonFragment()
                        })
                }
            }
        }
    }
}
