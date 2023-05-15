package com.gabor.pizzas.presentation.order.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gabor.pizzas.domain.model.Pizza

@Composable
fun PizzaList(
    pizzas: List<Pizza>,
    onPizzaClick: (pizza: Pizza) -> Unit,
) {
    LazyColumn(
            modifier = Modifier.padding(4.dp)
        ) {
        items(pizzas) { pizza ->
            PizzaItem(pizza = pizza, onSelect = onPizzaClick)
        }
    }
}