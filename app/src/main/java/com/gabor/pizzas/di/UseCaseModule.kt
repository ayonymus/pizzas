package com.gabor.pizzas.di

import com.gabor.pizzas.domain.usecase.CreateHalfPizzaUseCase
import com.gabor.pizzas.domain.usecase.FetchPizzasUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CreateHalfPizzaUseCase() }
    factory { FetchPizzasUseCase(get()) }
}