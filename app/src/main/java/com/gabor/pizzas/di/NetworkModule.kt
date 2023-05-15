package com.gabor.pizzas.di

import com.gabor.pizzas.data.remote.PizzaApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideGsonConverterFactory() }
    factory { provideRetrofit(get(), get()) }
    factory { providePizzaApi(get()) }
}

fun providePizzaApi(retrofit: Retrofit) = retrofit.create(PizzaApi::class.java)

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(loggingInterceptor)
    }
    return builder.build()
}

fun provideGsonConverterFactory(): GsonConverterFactory {
    val gsonBuilder = GsonBuilder()
    return GsonConverterFactory.create(gsonBuilder.create())
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(" https://static.mozio.com/")
    .client(okHttpClient)
    .addConverterFactory(gsonConverterFactory)
    .build()
