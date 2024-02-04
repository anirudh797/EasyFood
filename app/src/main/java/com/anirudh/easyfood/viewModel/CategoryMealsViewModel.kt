package com.anirudh.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.pojo.MealList
import com.anirudh.easyfood.pojo.MealsByCategory
import com.anirudh.easyfood.pojo.MealsByCategoryList
import com.anirudh.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {

    private var mealDetailLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName)
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    if (response.body() != null) {
                        mealDetailLiveData.postValue(response.body()?.meals)
                    }
                }

                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e("Anirudh CategoryMealsVm", "${t.message.toString()}")
                }

            })
    }

    fun observeMealsLiveData(): LiveData<List<MealsByCategory>> {
        return mealDetailLiveData
    }

}