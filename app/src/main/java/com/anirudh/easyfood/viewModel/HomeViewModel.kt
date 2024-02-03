package com.anirudh.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.pojo.MealList
import com.anirudh.easyfood.pojo.PopularMeal
import com.anirudh.easyfood.pojo.PopularMealList
import com.anirudh.easyfood.retrofit.RetrofitInstance
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    var randomMealLiveData = MutableLiveData<Meal>()
    var popularItemsLiveData = MutableLiveData<List<PopularMeal>>()
    fun getRandomMeal() = run {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    var randomMeal = response.body()!!.meals[0]
                    Log.d("Anirudh", randomMeal.toString())
                    randomMealLiveData.postValue(randomMeal)

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("Anirudh", t.message.toString())
            }

        })
    }

    fun getPopularItems() = run {
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<PopularMealList> {
            override fun onResponse(
                call: Call<PopularMealList>,
                response: Response<PopularMealList>
            ) {
                if (response.body() != null) {
                    Log.d("Anirudh"," PopularMeals ${response.body()?.meals?.size}")

                    popularItemsLiveData.postValue(response.body()?.meals)
                }

            }

            override fun onFailure(call: Call<PopularMealList>, t: Throwable) {
                Log.d("Anirudh", t.message.toString())
            }

        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularMealsLiveData(): LiveData<List<PopularMeal>> {
        return popularItemsLiveData
    }
}