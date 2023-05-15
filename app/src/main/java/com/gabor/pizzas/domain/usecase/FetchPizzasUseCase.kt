package com.gabor.pizzas.domain.usecase

import com.gabor.pizzas.data.repository.CachingRepository
import com.gabor.pizzas.domain.model.Pizza

class FetchPizzasUseCase(
   private val repository: CachingRepository<List<Pizza>>
) {
    suspend operator fun invoke() = repository.fetchData(true)
}
