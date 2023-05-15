package com.gabor.pizzas.presentation.summary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gabor.pizzas.R
import com.gabor.pizzas.presentation.order.ui.PizzaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(uiState: SummaryUiState) {

    Scaffold(
        topBar =  {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.order_summary))
            })
        },
        content = { contentPadding ->
            Column( modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()) {

                PizzaItem(pizza = uiState.pizza1, onSelect = { })

                if ( uiState.pizza2 != null) {
                    PizzaItem(pizza = uiState.pizza2, onSelect = { })
                }

                Text(text = stringResource(R.string.total))
                Text(text = "$ ${uiState.total}")
            }
        }
    )
}