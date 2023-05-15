package com.gabor.pizzas.presentation.order.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gabor.pizzas.R
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.presentation.order.PizzaOrderUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun OrderScreen(
    state: State<PizzaOrderUiState>,
    onSelectClicked: (pizza: Pizza) -> Unit,
    onOrderClicked: (pizza: Pizza, pizza2: Pizza?) -> Unit,
    onClearClicked: () -> Unit,
    onRefresh: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullRefreshState(state.value.isLoading, onRefresh)


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar =  {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.select_flavor))
            })
        },
        content = { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Column() {
                    OrderControlBox(
                        pizzaOrderUiState = state.value,
                        onOrderClicked = onOrderClicked,
                        onClearClicked = onClearClicked
                    )
                    PizzaList(
                        pizzas = state.value.availablePizzaList,
                        onPizzaClick = onSelectClicked
                    )
                }
            }
        }
    )
}