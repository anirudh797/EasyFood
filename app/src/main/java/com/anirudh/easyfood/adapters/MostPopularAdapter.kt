package com.anirudh.easyfood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.anirudh.easyfood.databinding.PopularItemBinding
import com.anirudh.easyfood.pojo.PopularMeal
import com.bumptech.glide.Glide

class MostPopularAdapter(var onItemClick: (PopularMeal) -> Unit) :
    RecyclerView.Adapter<MostPopularAdapter.PopularItemViewHolder>() {

    private var mealsList = ArrayList<PopularMeal>()

    fun setMeals(mealsList: ArrayList<PopularMeal>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    class PopularItemViewHolder(val binding: PopularItemBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularItemViewHolder {
        return PopularItemViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularItemViewHolder, position: Int) {
        val meal = mealsList[position]
        Glide.with(holder.itemView).load(
            meal.strMealThumb
        ).into(holder.binding.ivItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(meal)
        }
    }
}