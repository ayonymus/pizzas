package com.gabor.pizzas.data.local

import com.gabor.pizzas.data.repository.LocalDataSource
import com.gabor.pizzas.domain.model.Pizza

/**
 * This is a simple in-memory cache, but it would be straigt forward to use Room here
 * */
class PizzaLocalDataSource: LocalDataSource<List<Pizza>> {

    private var cache: List<Pizza> = emptyList()

    override suspend fun fetch() = Result.success(cache)

    override suspend fun update(data: List<Pizza>) {
        cache = data
    }
}
