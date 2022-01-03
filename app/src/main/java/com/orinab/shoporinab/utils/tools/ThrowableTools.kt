package com.orinab.shoporinab.utils.tools

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orinab.shoporinab.R
import com.orinab.shoporinab.utils.application.AppShopOrinab
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.util.*


class ThrowableTools(private val networkTools: NetworkTools,private val gson: Gson) {

    fun getThrowableError(throwable: Throwable): String {
        return initResultException(throwable)
    }

    private fun initResultException(throwable: Throwable): String {
        return when {
            networkTools.isNetworkAvailable -> AppShopOrinab.context.getString(R.string.no_connection)
            throwable is HttpException -> initHttpException(throwable)
            throwable is SocketTimeoutException -> AppShopOrinab.context.getString(R.string.time_out)
            else -> throwable.message.toString()
        }
    }

    private fun initHttpException(throwable: Throwable): String {
        val responseBody = Objects.requireNonNull((throwable as HttpException).response())?.errorBody()
        return try {
//            val type = object : TypeToken<ResponseErrorModel?>() {}.type
//            val errorModel: ResponseErrorModel =
//                gson.fromJson(Objects.requireNonNull(responseBody)!!.charStream(), type)
//            errorModel.message
            throwable.message!!
        } catch (e: Exception) {
            throwable.message!!
        }
    }

}