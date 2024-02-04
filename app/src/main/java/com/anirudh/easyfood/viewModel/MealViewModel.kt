package com.anirudh.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.easyfood.db.MealDb
import com.anirudh.easyfood.pojo.Meal
import com.anirudh.easyfood.pojo.MealList
import com.anirudh.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
    private val mealDatabase: MealDb?
) : ViewModel() {

    private var mealDetailLiveData = MutableLiveData<Meal>()


    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    mealDetailLiveData.value = response.body()?.meals?.get(0)
                } else
                    return

            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }


        })
    }

    fun observeMealDetailLiveData(): LiveData<Meal> {
        return mealDetailLiveData
    }

    fun insertMeal(meal: Meal) {
        viewModelScope.launch {
        mealDatabase?.mealDao()?.upsert(meal)
        }
    }


}