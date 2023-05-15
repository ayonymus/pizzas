package com.gabor.pizzas.domain.usecase

import com.gabor.pizzas.domain.model.Pizza

class CalculateMultiToppingPizzaPriceUseCase {

    operator fun invoke(pizza1: Pizza, pizza2: Pizza?): Double {
        return if (pizza2 != null) {
            pizza1.price / 2 + pizza2.price / 2
        } else {
            pizza1.price
        }
    }

}