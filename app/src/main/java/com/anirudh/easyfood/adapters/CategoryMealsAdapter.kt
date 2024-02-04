package com.anirudh.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.anirudh.easyfood.databinding.MealItemBinding
import com.anirudh.easyfood.databinding.PopularItemBinding
import com.anirudh.easyfood.pojo.MealsByCategory
import com.bumptech.glide.Glide

class CategoryMealsAdapter(var onItemClick: (MealsByCategory) -> Unit) :
    RecyclerView.Adapter<CategoryMealsAdapter.MealCategoryItemViewHolder>() {

    private var mealsList = ArrayList<MealsByCategory>()

    fun setMeals(mealsList: ArrayList<MealsByCategory>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    class MealCategoryItemViewHolder(val binding: MealItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryItemViewHolder {
        return MealCategoryItemViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: MealCategoryItemViewHolder, position: Int) {
        val meal = mealsList[position]
        Glide.with(holder.itemView).load(
            meal.strMealThumb
        ).into(holder.binding.ivMeal)

        holder.binding.tvMealName.text = meal.strMeal
        holder.itemView.setOnClickListener {
            onItemClick.invoke(meal)
        }
    }
}