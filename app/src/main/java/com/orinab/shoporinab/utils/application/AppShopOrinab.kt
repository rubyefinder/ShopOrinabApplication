package com.orinab.shoporinab.utils.application

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.developer.crashx.config.CrashConfig
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.room.UserDatabase
import com.orinab.shoporinab.di.*
import com.orinab.shoporinab.ui.viewmodel.UserViewModel
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.TOKEN_APP
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppShopOrinab : MultiDexApplication() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Activity

        fun initToken(): String {
            if (TOKEN_APP.isEmpty())
                TOKEN_APP = UserDatabase.getInstance().userDAO().getUserLocal()!!.token
            return TOKEN_APP
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
       // CrashConfig.Builder.create().apply()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = this
        startKoin {
            androidContext(context)
            modules(listOf(appModule, adapterModule, repoModule, viewModelModule, DatabaseModule))
        }
    }
}