package com.orinab.shoporinab.ui.view.adapter.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.flexbox.*
import com.orinab.shoporinab.data.model.response.dashboard.Banner
import com.orinab.shoporinab.data.model.response.dashboard.Value
import com.orinab.shoporinab.databinding.ItemCategoryListBinding
import com.orinab.shoporinab.databinding.ItemSliderBinding
import com.orinab.shoporinab.databinding.ItemSubListBinding
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.ui.view.adapter.category.CategoryAdapter
import com.orinab.shoporinab.ui.view.adapter.sub.SubAdapter
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TYPE_INT_CATEGORY_LIST
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TYPE_INT_SLIDER
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TYPE_INT_SUB_LIST
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TYPE_SLIDER
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TYPE_SUB_LIST
import com.orinab.shoporinab.utils.manager.GridLayoutCountManager
import com.orinab.shoporinab.utils.tools.GlideTools
import java.util.*

class DashboardAdapter(
    private val context: Context,
    private val glideTools: GlideTools,
    private val gridLayoutCountManager: GridLayoutCountManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<Value> = ArrayList()
    private lateinit var onClickListenerAny: OnClickListenerAny

    fun setOnClickItem(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_INT_SLIDER -> {
                val itemSliderBinding =
                    ItemSliderBinding.inflate(LayoutInflater.from(context), parent, false)
                BannerViewHolder(itemSliderBinding)
            }
            TYPE_INT_SUB_LIST -> {
                val itemSubListBinding =
                    ItemSubListBinding.inflate(LayoutInflater.from(context), parent, false)
                SubViewHolder(itemSubListBinding)
            }
            else -> {
                val itemCategoryListBinding =
                    ItemCategoryListBinding.inflate(LayoutInflater.from(context), parent, false)
                CategoryViewHolder(itemCategoryListBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is BannerViewHolder -> holder.bind(item)
            is SubViewHolder -> holder.bind(item)
            is CategoryViewHolder -> holder.bind(item)
        }

    }

    override fun getItemCount(): Int = list.size

    fun updateList(listValue: List<Value>) {
        list.clear()
        list.addAll(listValue)
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            TYPE_SLIDER -> TYPE_INT_SLIDER
            TYPE_SUB_LIST -> TYPE_INT_SUB_LIST
            else -> TYPE_INT_CATEGORY_LIST
        }

    }

    internal inner class BannerViewHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Value) {
            initBanner(value.banner)
        }

        private fun initBanner(banner: Banner) = when (banner.type) {
            0 -> initImageBanner(binding,banner)
            else -> initLottieBanner(binding,banner)
        }

        private fun initLottieBanner(binding: ItemSliderBinding, banner: Banner) {
           // binding.imgBanner.visibility= View.GONE

        }

        private fun initImageBanner(binding: ItemSliderBinding, banner: Banner) {
            binding.imgBanner.visibility= View.VISIBLE
            glideTools.displayHome(binding.imgBanner,banner.img)
            onClickListenerAny.onClickListener(banner)
        }
    }

    internal inner class SubViewHolder(private val binding: ItemSubListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Value) {
           initAdapter(binding,value)
        }

        private fun initAdapter(binding: ItemSubListBinding, value: Value) {
            val subAdapter=SubAdapter(context)
            val layoutManager =
                LinearLayoutManager(AppShopOrinab.context, RecyclerView.VERTICAL, false)
            binding.rcSubList.layoutManager = layoutManager
            binding.rcSubList.setHasFixedSize(true)
            binding.rcSubList.adapter = subAdapter
            subAdapter.setOnClickItem(object : OnClickListenerAny {
                override fun onClickListener(any: Any) {
                    onClickListenerAny.onClickListener(any)
                }
            })
            subAdapter.updateList(value.subList)
        }
    }

    internal inner class CategoryViewHolder(private val binding: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Value) {
            initHeader(binding,value)
            initAdapter(binding,value)
        }

        private fun initHeader(binding: ItemCategoryListBinding, value: Value) {
            binding.itemHeader.txtTitle.text=value.header.title
            binding.itemHeader.txtDescription.text=value.header.description
        }

        private fun initAdapter(binding: ItemCategoryListBinding, value: Value) {
            val categoryAdapter=CategoryAdapter(context,glideTools)
            binding.rcCategoryList.layoutManager =  LinearLayoutManager(AppShopOrinab.context, RecyclerView.HORIZONTAL, false)
            binding.rcCategoryList.setHasFixedSize(true)
            binding.rcCategoryList.adapter = categoryAdapter
            categoryAdapter.setOnClickItem(object : OnClickListenerAny {
                override fun onClickListener(any: Any) {
                    onClickListenerAny.onClickListener(any)
                }
            })
            categoryAdapter.updateList(value.categoryList)
        }


    }
}