@file:OptIn(ExperimentalMaterial3Api::class)

package com.gabor.pizzas.presentation.order.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabor.pizzas.domain.model.Pizza


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaItem(
    pizza: Pizza,
    onSelect: (pizza: Pizza) -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(4.dp),
        onClick = { onSelect(pizza) }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = pizza.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )

            Text(
                text = "$ ${pizza.price}",
                color = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPizzaItem() {
    PizzaItem(pizza = (Pizza("Pepperoni", 10.0) ), onSelect = { })
}
