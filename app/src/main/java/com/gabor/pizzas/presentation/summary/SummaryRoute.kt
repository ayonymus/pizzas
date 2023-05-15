package com.gabor.pizzas.presentation.summary

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SummaryRoute(
    viewModel: SummaryViewModel = koinViewModel(),
) {
    SummaryScreen(viewModel.uiState)
}