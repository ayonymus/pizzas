package com.gabor.pizzas.di

import com.gabor.pizzas.data.local.PizzaLocalDataSource
import com.gabor.pizzas.data.remote.PizzaApi
import com.gabor.pizzas.data.remote.PizzaRemoteDataSource
import com.gabor.pizzas.data.repository.CachingRepository
import com.gabor.pizzas.data.repository.LocalDataSource
import com.gabor.pizzas.data.repository.RemoteDataSource
import com.gabor.pizzas.domain.model.Pizza
import org.koin.dsl.module

val dataModule = module {
    single { provideLocalPizzaSource() }
    factory { provideRemotePizzaSource(get()) }
    factory { providePizzaRepository(get(), get()) }
}

fun providePizzaRepository(local: LocalDataSource<List<Pizza>>, remote: RemoteDataSource<List<Pizza>>) =
    object : CachingRepository<List<Pizza>>(local, remote) { }

fun provideLocalPizzaSource() = PizzaLocalDataSource()

fun provideRemotePizzaSource(api: PizzaApi) = PizzaRemoteDataSource(api)
