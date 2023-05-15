package com.gabor.pizzas.presentation.summary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.domain.usecase.CalculateMultiToppingPizzaPriceUseCase

class SummaryViewModel(
    private val calculatePizzaPriceUseCase: CalculateMultiToppingPizzaPriceUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pizza1Name: String = checkNotNull(savedStateHandle["mainName"])
    private val pizza1Price: Float = checkNotNull(savedStateHandle["mainPrice"])

    private val pizza1 = Pizza(pizza1Name, pizza1Price.toDouble())

    private val pizza2Name: String? = savedStateHandle["otherName"]
    private val pizza2Price: Float? = savedStateHandle["otherPrice"]

    private val pizza2 = if (pizza2Name != null && pizza2Price != null) {
        Pizza(pizza2Name, pizza2Price.toDouble())
    } else {
        null
    }

    // This UI is dumb, no need for flows
    val uiState = SummaryUiState(
        pizza1 = pizza1,
        pizza2 = pizza2,
        total = calculatePizzaPriceUseCase(pizza1, pizza2)
    )
}

data class SummaryUiState(
    val pizza1: Pizza,
    val pizza2: Pizza?,
    val total: Double
)