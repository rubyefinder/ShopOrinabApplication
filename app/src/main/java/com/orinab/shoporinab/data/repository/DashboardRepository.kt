package com.orinab.shoporinab.data.repository

import com.orinab.shoporinab.data.api.ApiHelper

class DashboardRepository(private val apiHelper: ApiHelper) {

    suspend fun initDashboard() = apiHelper.initDashboard()
    suspend fun initCabinetKitchen(id: Int) = apiHelper.initCabinetKitchen(id)
    suspend fun initCabinetKitchenSingle(url: String) = apiHelper.initCabinetKitchenSingle(url)



}