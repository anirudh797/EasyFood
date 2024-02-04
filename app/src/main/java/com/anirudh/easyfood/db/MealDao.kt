package com.anirudh.easyfood.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.anirudh.easyfood.pojo.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal: Meal) //insert and update

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealInfo ")
    fun getAllMeals(): LiveData<List<Meal>>

}