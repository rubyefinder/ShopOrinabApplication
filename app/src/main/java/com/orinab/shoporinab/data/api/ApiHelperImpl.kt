package com.orinab.shoporinab.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun initDashboard(): Any =
        try {
            apiService.initDashboard()
        } catch (e: Exception) {
            e
        }

    override suspend fun initCabinetKitchen(id: Int):  Any =
        try {
            apiService.initCabinetKitchen(id)
        } catch (e: Exception) {
            e
        }

    override suspend fun initCabinetKitchenSingle(url: String): Any =
        try {
            apiService.initCabinetKitchenSingle(url)
        } catch (e: Exception) {
            e
        }
}