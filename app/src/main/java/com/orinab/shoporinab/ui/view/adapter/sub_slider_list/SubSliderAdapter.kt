package com.orinab.shoporinab.ui.view.adapter.sub_slider_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.orinab.shoporinab.data.model.local.sub_slider.ItemSubSlider
import com.orinab.shoporinab.databinding.*
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.extention.getHeightDisplayMetrics
import com.orinab.shoporinab.utils.extention.getWidthDisplayMetrics
import com.orinab.shoporinab.utils.tools.GlideTools
import java.util.*

class SubSliderAdapter(
    private val context: Context,
    private val glideTools: GlideTools,
) : RecyclerView.Adapter<SubSliderAdapter.SubSliderViewHolder>() {

    private var list: ArrayList<ItemSubSlider> = ArrayList()
    private lateinit var onClickListenerAny: OnClickListenerAny

    fun setOnClickItem(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubSliderAdapter.SubSliderViewHolder {
        val itemSubSliderBinding =
            ItemSubSliderBinding.inflate(LayoutInflater.from(context), parent, false)
        return SubSliderViewHolder(itemSubSliderBinding)
    }

    override fun onBindViewHolder(holder: SubSliderAdapter.SubSliderViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listSubSlider: List<ItemSubSlider>) {
        list.clear()
        list.addAll(listSubSlider)
        notifyDataSetChanged()
    }

    fun activeItem(position: Int) {
        list.forEach { it.position = -1 }
        if (position < list.size)
            list[position].position = position
        notifyDataSetChanged()
    }

    inner class SubSliderViewHolder(private val binding: ItemSubSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemSubSlider: ItemSubSlider, position: Int) {
            glideTools.displayImageSubSlider(binding.imgCategory, itemSubSlider.item)
            binding.lnParent.setOnClickListener { onClickListenerAny.onClickListener(position) }
            initSizeDensity(binding.imgCategory)
            initCheckViewActiveItem(binding, itemSubSlider, position)
        }

        private fun initSizeDensity(imgCategory: ImageView) {
            val width = getWidthDisplayMetrics(AppShopOrinab.activity)
            val height = getHeightDisplayMetrics(AppShopOrinab.activity)
            val param: ViewGroup.LayoutParams = imgCategory.layoutParams
            param.width = (width / 2.1).toInt()
            param.height = (height / 4)
            imgCategory.layoutParams = param
        }

        private fun initCheckViewActiveItem(
            binding: ItemSubSliderBinding,
            itemSubSlider: ItemSubSlider,
            position: Int
        ) = when (itemSubSlider.position) {
            position -> binding.view.visibility = View.VISIBLE
            else -> binding.view.visibility = View.INVISIBLE
        }
    }
}