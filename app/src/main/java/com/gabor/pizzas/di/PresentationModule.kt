package com.gabor.pizzas.di

import com.gabor.pizzas.presentation.order.PizzaOrderViewModel
import com.gabor.pizzas.presentation.summary.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PizzaOrderViewModel(get()) }
    viewModel { SummaryViewModel (get(), get()) }
}