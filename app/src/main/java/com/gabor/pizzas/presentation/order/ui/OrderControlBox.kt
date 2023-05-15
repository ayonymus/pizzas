package com.gabor.pizzas.presentation.order.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gabor.pizzas.R
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.presentation.order.PizzaOrderUiState

@Composable
fun OrderControlBox(
    pizzaOrderUiState: PizzaOrderUiState,
    onOrderClicked: (pizza: Pizza, pizza2: Pizza?) -> Unit,
    onClearClicked: () -> Unit
) {
    Column() {
        if (pizzaOrderUiState.selectedPizza1 == null) {
            Text(stringResource(id = R.string.select_a_pizza))
        } else {
            Text(pizzaOrderUiState.selectedPizza1.name)
        }

        if (pizzaOrderUiState.selectedPizza2 == null) {
            Text(stringResource(id = R.string.select_another))
        } else {
            Text(pizzaOrderUiState.selectedPizza2.name)
        }

        if (pizzaOrderUiState.selectedPizza1 != null) {
            Button(
                onClick = { onOrderClicked(pizzaOrderUiState.selectedPizza1, pizzaOrderUiState.selectedPizza2) }) {
                Text(stringResource(id = R.string.order))
            }
        }

        if (pizzaOrderUiState.selectedPizza1 != null) {
            Button(
                onClick = onClearClicked) {
                Text(stringResource(id = R.string.clear))
            }
        }
    }


}