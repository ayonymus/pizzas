package com.gabor.pizzas.di

import com.gabor.pizzas.domain.usecase.CalculateMultiToppingPizzaPriceUseCase
import com.gabor.pizzas.domain.usecase.FetchPizzasUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CalculateMultiToppingPizzaPriceUseCase() }
    factory { FetchPizzasUseCase(get()) }
}