package com.gabor.pizzas.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface PizzaApi {

    @GET("mobile/tests/pizzas.json")
    suspend fun getPizzas(): Response<List<RemotePizza>>

}

data class RemotePizza(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double
)