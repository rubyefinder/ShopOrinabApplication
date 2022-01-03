package com.orinab.shoporinab.data.model.response.cabinet_kitchen


import com.google.gson.annotations.SerializedName

data class ResponseCabinetKitchenModel(
    @SerializedName("PRO")
    val pro: List<Pro>,
    @SerializedName("TIT")
    val tit: TIT
)