package com.gabor.pizzas.domain.usecase

import com.gabor.pizzas.domain.model.Pizza
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculateMultiToppingPizzaPriceUseCaseTest {

    // UseCase under test
    private val calculateMultiToppingPizzaPriceUseCase = CalculateMultiToppingPizzaPriceUseCase()

    @Test
    fun `given one pizza, when use case is invoked, then return pizza price`() {
        // Given
        val pizza = Pizza("Margarita", 10.0)

        // When
        val result = calculateMultiToppingPizzaPriceUseCase(pizza, null)

        // Then
        assertEquals(10.0, result, 0.0)
    }

    @Test
    fun `given two pizzas, when use case is invoked, then return half price of each pizza`() {
        // Given
        val pizza1 = Pizza("Margarita", 10.0)
        val pizza2 = Pizza("Pepperoni", 12.0)

        // When
        val result = calculateMultiToppingPizzaPriceUseCase(pizza1, pizza2)

        // Then
        assertEquals(11.0, result, 0.0)
    }
}