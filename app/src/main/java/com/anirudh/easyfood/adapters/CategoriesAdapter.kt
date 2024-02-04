package com.anirudh.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.easyfood.databinding.CategoryItemBinding
import com.anirudh.easyfood.pojo.MealCategory
import com.bumptech.glide.Glide

class CategoriesAdapter(val onItemClick: (MealCategory) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList = ArrayList<MealCategory>()

    fun setMealCategories(mealCategories: ArrayList<MealCategory>) {
        this.categoriesList = mealCategories
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        Glide.with(holder.itemView).load(category.strCategoryThumb).into(
            holder.binding.ivCategory
        )
        holder.binding.tvCategoryName.text = category.strCategory

        holder.binding.root.setOnClickListener {
            onItemClick.invoke(category)
        }
    }
}