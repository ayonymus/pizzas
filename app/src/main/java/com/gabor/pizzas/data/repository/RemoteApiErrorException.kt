package com.gabor.pizzas.data.repository

class RemoteApiErrorException(
    message: String? = null,
    val code: Int,
    val errorBody: String? = null
): Exception(message)