package com.anirudh.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.easyfood.databinding.MealItemBinding
import com.anirudh.easyfood.pojo.Meal

import com.anirudh.easyfood.pojo.MealsByCategory
import com.bumptech.glide.Glide

class FavoritesAdapter(private var onItemClick: (Meal) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

     var mealsList = ArrayList<Meal>()

    fun setMeals(mealsList: ArrayList<Meal>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
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

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val meal = mealsList[position]
        Glide.with(holder.itemView).load(
            meal.strMealThumb
        ).into(holder.binding.ivMeal)
        holder.binding.tvMealName.text = meal.strMeal

//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(meal)
//        }
    }
}