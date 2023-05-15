package com.gabor.pizzas.domain.usecase

import com.gabor.pizzas.domain.model.Pizza

class CalculatePizzaPriceUseCase {

    operator fun invoke(pizzas: List<Pizza>) = pizzas.sumOf { it.price }

}