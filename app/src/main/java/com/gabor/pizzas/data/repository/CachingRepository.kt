package com.gabor.pizzas.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A generic repository that backs up data fetched from remote into a local cache.
 */
abstract class CachingRepository<Data>(
    private val localDataSource: LocalDataSource<Data>,
    private val remoteDataSource: RemoteDataSource<Data>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) {

    /**
     * First fetch from local data source.
     * If not available or refresh is `true`, try fetching from remote.
     * Once new data is available, write to database.
     */
    suspend fun fetchData(refresh: Boolean): Result<Data> = withContext(dispatcher) {
        if (refresh) {
            val result = remoteDataSource.fetch()
            result.fold(
                onSuccess = {
                    localDataSource.update(it)
                    return@withContext result
                },
                onFailure = {
                    return@withContext Result.failure(it)
                }
            )
        }
        return@withContext localDataSource.fetch()
    }
}

interface LocalDataSource<Data> {

    suspend fun fetch(): Result<Data>

    suspend fun update(data: Data)

}

interface RemoteDataSource<Data> {

    suspend fun fetch(): Result<Data>

}
