package com.gabor.pizzas.presentation.order

import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.domain.usecase.FetchPizzasUseCase
import com.gabor.pizzas.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PizzaOrderViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    // Mock use case
    private val fetchPizzasUseCase: FetchPizzasUseCase = mock()

    // ViewModel under test
    private lateinit var viewModel: PizzaOrderViewModel

    @Before
    fun setup() {
        viewModel = PizzaOrderViewModel(fetchPizzasUseCase)
    }

    @Test
    fun `given fetch intent, when handleIntent is called, then fetch pizzas`() = runTest {
        // Given
        val pizzaList = listOf(
            Pizza("Margarita", 10.0),
            Pizza("Pepperoni", 12.0)
        )
        whenever(fetchPizzasUseCase()).thenReturn(Result.success(pizzaList))

        // When
        viewModel.handleIntent(PizzaOrderIntent.Fetch)

        // Then
        //delay(100) // Allow async updates to propagate
        val state = viewModel.orderFlow.first()
        assertEquals(pizzaList, state.availablePizzaList)
        assertFalse(state.isLoading)
        assertFalse(state.hasErrors)
    }

    @Test
    fun `given select intent, when handleIntent is called, then select pizza`() = runTest {
        // Given
        val pizza = Pizza("Margarita", 10.0)

        // When
        viewModel.handleIntent(PizzaOrderIntent.Select(pizza))

        // Then
        //delay(100) // Allow async updates to propagate
        val state = viewModel.orderFlow.first()
        assertEquals(pizza, state.selectedPizza1)
    }

    @Test
    fun `given clear intent, when handleIntent is called, then clear selection`() = runTest {
        // Given
        val pizza = Pizza("Margarita", 10.0)
        viewModel.handleIntent(PizzaOrderIntent.Select(pizza))

        // When
        viewModel.handleIntent(PizzaOrderIntent.Clear)

        // Then
        //delay(100) // Allow async updates to propagate
        val state = viewModel.orderFlow.first()
        assertNull(state.selectedPizza1)
        assertNull(state.selectedPizza2)
    }
}