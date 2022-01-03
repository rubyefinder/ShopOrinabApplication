package com.orinab.shoporinab.utils.tools

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.orinab.shoporinab.R
import com.orinab.shoporinab.utils.application.AppShopOrinab


@Suppress("DEPRECATION")
class ToastTools {

    fun toast(message: String) {
        val layout: View = AppShopOrinab.activity.layoutInflater.inflate(
            R.layout.toast_layout,
            AppShopOrinab.activity.findViewById<View>(R.id.custom_toast_layout_id) as ViewGroup?)
        val text = layout.findViewById<View>(R.id.text) as TextView
        text.setTextColor(Color.WHITE)
        text.text = message
        val lytCard = layout.findViewById<View>(R.id.lyt_card) as CardView
        lytCard.setCardBackgroundColor(AppShopOrinab.activity.resources.getColor(R.color.colorBlack))

        val toast = Toast(AppShopOrinab.activity)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}