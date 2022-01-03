package com.orinab.shoporinab.data.api

import com.orinab.shoporinab.data.model.response.cabinet_kitchen.ResponseCabinetKitchenModel
import com.orinab.shoporinab.data.model.response.cabinet_kitchen_single.ResponseCabinetKitchenSingle
import com.orinab.shoporinab.data.model.response.dashboard.ResponseDashboardModel
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.CABINET_KITCHEN
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.CABINET_KITCHEN_SINGLE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.DASHBOARD
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.ID
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET(DASHBOARD)
    suspend fun initDashboard(): ResponseDashboardModel

    @GET(CABINET_KITCHEN)
    suspend fun initCabinetKitchen(@Query(ID) id: Int): ResponseCabinetKitchenModel

    @GET(CABINET_KITCHEN_SINGLE)
    suspend fun initCabinetKitchenSingle(@Query(URL) url: String): ResponseCabinetKitchenSingle





}