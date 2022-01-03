package com.orinab.shoporinab.data.api



interface ApiHelper {


    suspend fun initDashboard(): Any
    suspend fun initCabinetKitchen(id: Int): Any
    suspend fun initCabinetKitchenSingle(url: String): Any
}