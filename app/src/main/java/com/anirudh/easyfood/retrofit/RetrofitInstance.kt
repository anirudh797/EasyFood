package com.anirudh.easyfood.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    const val BASE_URL = "http://www.themealdb.com/api/json/v1/1/"
    val api: MealApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MealApi::class.java)
    }
}