package com.gabor.pizzas.presentation.order

import android.text.method.TextKeyListener.clear
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.domain.usecase.CalculatePizzaPriceUseCase
import com.gabor.pizzas.domain.usecase.CreateHalfPizzaUseCase
import com.gabor.pizzas.domain.usecase.FetchPizzasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import timber.log.Timber

class PizzaOrderViewModel(
    private val fetchPizzasUseCase: FetchPizzasUseCase,
    private val createHalfPizzaUseCase: CreateHalfPizzaUseCase,
    private val calculatePizzaPriceUseCase: CalculatePizzaPriceUseCase
) : ViewModel() {

    private val _orderFlow = MutableStateFlow(PizzaOrderUiState(isLoading = true))
    val orderFlow: StateFlow<PizzaOrderUiState> = _orderFlow

    fun handleIntent(intent: PizzaOrderIntent) {
        when (intent) {
            is PizzaOrderIntent.Fetch -> fetch()
            is PizzaOrderIntent.Select -> selectPizza(intent.pizza)
            is PizzaOrderIntent.AddHalf -> addHalf(intent.pizza)
            is PizzaOrderIntent.Clear -> clearSelection()
        }
    }

    private fun clearSelection() {
        viewModelScope.launch {
            _orderFlow.update {
                it.copy(
                    selectedPizza1 = null,
                    selectedPizza2 = null
                )
            }
        }
    }

    private fun addHalf(pizza: Pizza) {
        viewModelScope.launch {
            _orderFlow.update { it.copy(selectedPizza2 = pizza) }
        }
    }

    private fun selectPizza(selected: Pizza) {
        viewModelScope.launch {
            _orderFlow.update { it.copy(selectedPizza1 = selected) }
        }
    }

    private fun fetch() {
        viewModelScope.launch {
            _orderFlow.update { it.copy(isLoading = true) }
            handleResult(fetchPizzasUseCase())
        }
    }

    private suspend fun handleResult(result: Result<List<Pizza>>) {
        result.fold(
            onSuccess = { data ->
                _orderFlow.update {
                    it.copy(
                        isLoading = false,
                        availablePizzaList = data
                    )
                }
            },
            onFailure = {
                Timber.e(it)
                _orderFlow.update {
                    it.copy(
                        isLoading = false,
                        hasErrors = true
                    )
                }
            }
        )
    }

}

private suspend fun MutableStateFlow<PizzaOrderUiState>.update(
    action: (PizzaOrderUiState) -> PizzaOrderUiState
) {
    this.emit(action(this.value))
}


data class PizzaOrderUiState(
    val hasErrors: Boolean = false,
    val isLoading: Boolean = false,
    val availablePizzaList: List<Pizza> = emptyList(),
    val selectedPizza1: Pizza? = null,
    val selectedPizza2: Pizza? = null,
    val selectedName: String = ""
)

sealed class PizzaOrderIntent {
    data class Fetch(val refresh: Boolean = false) : PizzaOrderIntent()
    data class Select(val pizza: Pizza) : PizzaOrderIntent()
    data class AddHalf(val pizza: Pizza) : PizzaOrderIntent()
    object Clear : PizzaOrderIntent()

}