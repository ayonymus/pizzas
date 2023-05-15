package com.gabor.pizzas.presentation.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.presentation.order.ui.OrderScreen
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun OrderRoute(
    viewModel: PizzaOrderViewModel = koinViewModel(),
    onNavigateToOrder: (pizza: Pizza, pizza2: Pizza?) -> Unit,

    ) {
    remember { viewModel.handleIntent(PizzaOrderIntent.Fetch) }
    OrderScreen(
        state = viewModel.orderFlow.collectAsState(),
        onSelectClicked = { viewModel.handleIntent(PizzaOrderIntent.Select(it)) },
        onOrderClicked =  onNavigateToOrder,
        onClearClicked = { viewModel.handleIntent(PizzaOrderIntent.Clear) },
        onRefresh = { viewModel.handleIntent(PizzaOrderIntent.Fetch) }
    )
}
