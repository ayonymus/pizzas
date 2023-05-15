package com.gabor.pizzas.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabor.pizzas.domain.model.Pizza
import com.gabor.pizzas.domain.usecase.FetchPizzasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class PizzaOrderViewModel(
    private val fetchPizzasUseCase: FetchPizzasUseCase
) : ViewModel() {

    private val _orderFlow = MutableStateFlow(PizzaOrderUiState(isLoading = true))
    val orderFlow: StateFlow<PizzaOrderUiState> = _orderFlow

    fun handleIntent(intent: PizzaOrderIntent) {
        Timber.d(intent.toString())
        when (intent) {
            is PizzaOrderIntent.Fetch -> fetch()
            is PizzaOrderIntent.Select -> selectPizza(intent.pizza)
            is PizzaOrderIntent.Clear -> clearSelection()
        }
    }

    private fun clearSelection() {
        viewModelScope.launch {
            _orderFlow.update {
                it.copy(
                    selectedPizza1 = null,
                    selectedPizza2 = null,
                    addSecondFlavor = false
                )
            }
        }
    }

    private fun selectPizza(selected: Pizza) {
        viewModelScope.launch {
            _orderFlow.update { state ->
                if (state.selectedPizza1 == null) {
                    state.copy(selectedPizza1 = selected)
                } else {
                    state.copy(selectedPizza2 = selected)

                }
            }
        }
    }

    private fun fetch() {
        viewModelScope.launch {
            _orderFlow.update { it.copy(isLoading = true) }
            handleResult(fetchPizzasUseCase())
        }
    }

    private suspend fun handleResult(result: Result<List<Pizza>>) {
        Timber.d(result.toString())
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

data class PizzaOrderUiState(
    val hasErrors: Boolean = false,
    val isLoading: Boolean = false,
    val availablePizzaList: List<Pizza> = emptyList(),
    val selectedPizza1: Pizza? = null,
    val selectedPizza2: Pizza? = null,
    val addSecondFlavor: Boolean = false
)

sealed class PizzaOrderIntent {
    object Fetch : PizzaOrderIntent()
    data class Select(val pizza: Pizza) : PizzaOrderIntent()
    object Clear : PizzaOrderIntent()
}