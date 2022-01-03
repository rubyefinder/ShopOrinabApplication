package com.orinab.shoporinab.ui.view.adapter.category

import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.response.dashboard.Category
import com.orinab.shoporinab.databinding.*
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.extention.*
import com.orinab.shoporinab.utils.extention.initPalletMoney
import com.orinab.shoporinab.utils.tools.GlideTools
import java.util.*

class CategoryAdapter(
    private val context: Context,
    private val glideTools: GlideTools,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var list: ArrayList<Category> = ArrayList()
    private lateinit var onClickListenerAny: OnClickListenerAny

    fun setOnClickItem(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        val itemCategoryBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(itemCategoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listCategory: List<Category>) {
        list.clear()
        list.addAll(listCategory)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            glideTools.displayImageOriginal(binding.imgCategory, category.imgFirst)
            binding.txtTitle.text = category.name
            binding.txtTitle.isSelected=true
            initPalletDescription(context, binding.txtDiscountPrice, category.model)
            initCheckDiscountMoney(context,binding.txtPrice,binding.txtDiscountPrice, category.discountPrice)
            initCheckMoney(category)
            initSizeDensity(binding.imgCategory)
            binding.lnParent.setOnClickListener { onClickListenerAny.onClickListener(category) }
        }

        private fun initCheckMoney(category: Category) {
            if (category.discountPrice.isNullOrEmpty()){
                initPalletMoney(binding.txtPrice, category.price)
                initPalletMoney(binding.txtDiscountPrice, category.discountPrice)
            }else{
                initPalletMoney(binding.txtDiscountPrice, category.price)
                initPalletMoney(binding.txtPrice, category.discountPrice)
            }
        }

        private fun initSizeDensity(imgCategory: ImageView) {
            val width = getWidthDisplayMetrics(AppShopOrinab.activity)
            val param: ViewGroup.LayoutParams = imgCategory.layoutParams
            param.width = (width/2.1).toInt()
            imgCategory.layoutParams = param
        }
    }
}