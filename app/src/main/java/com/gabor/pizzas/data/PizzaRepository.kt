package com.gabor.pizzas.data

import com.gabor.pizzas.data.repository.CachingRepository
import com.gabor.pizzas.data.repository.LocalDataSource
import com.gabor.pizzas.data.repository.RemoteDataSource
import com.gabor.pizzas.domain.model.Pizza

class PizzaRepository(
    private val localDataSource: LocalDataSource<List<Pizza>>,
    private val remoteDataSource: RemoteDataSource<List<Pizza>>
)
    : CachingRepository<List<Pizza>>(localDataSource, remoteDataSource)

