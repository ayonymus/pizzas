package com.gabor.pizzas.data.remote

import com.gabor.pizzas.data.repository.RemoteApiErrorException
import com.gabor.pizzas.data.repository.RemoteDataSource
import com.gabor.pizzas.domain.model.Pizza
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PizzaRemoteDataSource(
    private val api: PizzaApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO // overridable for unit testing
): RemoteDataSource<List<Pizza>> {

    override suspend fun fetch(): Result<List<Pizza>> = withContext(dispatcher) {
        return@withContext try {
            val response = api.fetchPizzas()
            val data = response.body()

            if (data != null) {
                Result.success(data.map { it.toDomain() })
            } else {
                Result.failure(RemoteApiErrorException(response.message(), response.code(), response.errorBody()?.toString()))
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}