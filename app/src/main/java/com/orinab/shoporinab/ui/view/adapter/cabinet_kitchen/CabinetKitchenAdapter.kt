package com.orinab.shoporinab.ui.view.adapter.cabinet_kitchen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.orinab.shoporinab.data.model.response.cabinet_kitchen.Pro
import com.orinab.shoporinab.databinding.*
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.extention.*
import com.orinab.shoporinab.utils.tools.GlideTools
import java.util.*

class CabinetKitchenAdapter(
    private val context: Context,
    private val glideTools: GlideTools,
) : RecyclerView.Adapter<CabinetKitchenAdapter.ProViewHolder>() {

    private var list: ArrayList<Pro> = ArrayList()
    private lateinit var onClickListenerAny: OnClickListenerAny

    fun setOnClickItem(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabinetKitchenAdapter.ProViewHolder {
        val itemProBinding =
            ItemProBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProViewHolder(itemProBinding)
    }

    override fun onBindViewHolder(holder: CabinetKitchenAdapter.ProViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listPro: List<Pro>) {
        list.clear()
        list.addAll(listPro)
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<Pro> = list



    inner class ProViewHolder(private val binding: ItemProBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pro: Pro) {
            glideTools.displayImageOriginal(binding.imgPro, pro.imgFirst)
            binding.txtTitle.text = pro.name
            binding.txtTitle.isSelected=true
            initPalletDescription(context, binding.txtDiscountPrice, pro.model)
            initCheckDiscountMoney(context,binding.txtPrice,binding.txtDiscountPrice, pro.discountPrice)
            initCheckMoney(pro)
            initSizeDensity(binding.imgPro)
            binding.lnParent.setOnClickListener { onClickListenerAny.onClickListener(pro) }
        }

        private fun initCheckMoney(pro: Pro) {
            if (pro.discountPrice.isNullOrEmpty()){
                initPalletMoney(binding.txtPrice, pro.price)
                initPalletMoney(binding.txtDiscountPrice, pro.discountPrice)
            }else{
                initPalletMoney(binding.txtDiscountPrice, pro.price)
                initPalletMoney(binding.txtPrice, pro.discountPrice)
            }
        }

        private fun initSizeDensity(imgPro: ImageView) {
            val width = getWidthDisplayMetrics(AppShopOrinab.activity)
            val param: ViewGroup.LayoutParams = imgPro.layoutParams
            param.width = (width/2.1).toInt()
            imgPro.layoutParams = param
        }
    }
}