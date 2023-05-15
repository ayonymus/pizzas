package com.gabor.pizzas.domain.usecase

import com.gabor.pizzas.domain.model.Pizza

class CreateHalfPizzaUseCase {

    operator fun invoke(pizza1: Pizza, pizza2: Pizza): Pizza {
        return Pizza(
            name = "${pizza1.name} | ${pizza2.name}",
            price = (pizza1.price / 2) + (pizza2.price / 2 )
        )
    }
}
