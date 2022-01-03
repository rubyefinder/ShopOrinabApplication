package com.orinab.shoporinab.utils.extention

import android.content.Context
import android.os.Bundle
import com.google.gson.Gson
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.local.bundle.BundleModel
import com.orinab.shoporinab.data.model.response.cabinet_kitchen.Pro
import com.orinab.shoporinab.data.model.response.dashboard.Category
import com.orinab.shoporinab.data.model.response.dashboard.Sub

fun initBundle(context: Context,gson: Gson,key:String,any: Any): Bundle {
    val bundle= Bundle()
    bundle.putString(key,initJsonAny(context,gson,any))
    return bundle
}

fun resultBundle(bundle :Bundle,key:String): String = bundle.getString(key,"")

fun initJsonAny(context: Context,gson: Gson, it: Any): String {
    return when(it){
        is Category->{  gson.toJson(BundleModel(it.id,it.url,context.resources.getString(R.string.product_description)))}
        is Sub ->{  gson.toJson(BundleModel(it.id,"",it.title))}
        is Pro ->{  gson.toJson(BundleModel(it.id,it.url,context.resources.getString(R.string.product_description)))}
        else -> ""
    }
}
