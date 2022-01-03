package com.orinab.shoporinab.ui.view.adapter.sub

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orinab.shoporinab.data.model.response.dashboard.Sub
import com.orinab.shoporinab.databinding.ItemSubBinding
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import java.util.*

class SubAdapter(
    private val context: Context,
) : RecyclerView.Adapter<SubAdapter.SubViewHolder>() {

    private var list: ArrayList<Sub> = ArrayList()
    private lateinit var onClickListenerAny: OnClickListenerAny

    fun setOnClickItem(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        val itemSubBinding =ItemSubBinding.inflate(LayoutInflater.from(context), parent, false)
        return SubViewHolder(itemSubBinding)

    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int = list.size

    fun updateList(listSub: List<Sub>) {
        list.clear()
        list.addAll(listSub)
        notifyDataSetChanged()
    }

    inner class SubViewHolder(private val binding: ItemSubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sub: Sub) {
            binding.txtTitle.text=sub.title
            binding.lnParent.setOnClickListener { onClickListenerAny.onClickListener(sub) }
        }
    }
}