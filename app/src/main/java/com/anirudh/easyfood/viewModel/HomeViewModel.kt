package com.anirudh.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.easyfood.db.MealDb
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.pojo.MealCategory
import com.anirudh.easyfood.pojo.MealCategoryList
import com.anirudh.easyfood.pojo.MealList
import com.anirudh.easyfood.pojo.MealsByCategory
import com.anirudh.easyfood.pojo.MealsByCategoryList
import com.anirudh.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val mealDb: MealDb
) : ViewModel() {

    var randomMealLiveData = MutableLiveData<Meal>()
    var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    var categoryListLiveData = MutableLiveData<List<MealCategory>>()
    var favoriteMealsLiveData = mealDb.mealDao().getAllMeals()
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
        RetrofitInstance.api.getPopularItems("Seafood")
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    if (response.body() != null) {
                        Log.d("Anirudh", " PopularMeals ${response.body()?.meals?.size}")

                        popularItemsLiveData.postValue(response.body()?.meals)
                    }

                }

                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.d("Anirudh", t.message.toString())
                }

            })
    }

    fun getCategoryList() = run {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<MealCategoryList> {
            override fun onResponse(
                call: Call<MealCategoryList>,
                response: Response<MealCategoryList>
            ) {
                if (response.body() != null) {
                    Log.d("Anirudh", " CategoryMeals ${response.body()?.categories}")
                    categoryListLiveData.postValue(response.body()?.categories)
                }

            }

            override fun onFailure(call: Call<MealCategoryList>, t: Throwable) {
                Log.d("Anirudh getCategoriesList", t.message.toString())
            }

        })
    }


    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularMealsLiveData(): LiveData<List<MealsByCategory>> {
        return popularItemsLiveData
    }

    fun observeCategoryListLiveData(): LiveData<List<MealCategory>> {
        return categoryListLiveData
    }

    fun observeFavoriteMealsLiveData(): LiveData<List<Meal>> {
        return favoriteMealsLiveData
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            mealDb.mealDao().delete(meal)
        }
    }

    fun insertMeal(meal: Meal) {
        viewModelScope.launch {
            mealDb.mealDao().upsert(meal)
        }
    }

}