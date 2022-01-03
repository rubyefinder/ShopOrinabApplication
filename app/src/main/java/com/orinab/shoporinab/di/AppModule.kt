package com.orinab.shoporinab.di

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.orinab.shoporinab.BuildConfig.DEBUG
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.api.ApiHelper
import com.orinab.shoporinab.data.api.ApiHelperImpl
import com.orinab.shoporinab.data.api.ApiService
import com.orinab.shoporinab.utils.application.AppShopOrinab.Companion.activity
import com.orinab.shoporinab.utils.application.AppShopOrinab.Companion.context
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BASE_URL

import com.orinab.shoporinab.utils.image.ZoomOutSlideTransformer
import com.orinab.shoporinab.utils.manager.GridLayoutCountManager
import com.orinab.shoporinab.utils.manager.KeyboardManager
import com.orinab.shoporinab.utils.music_manager.BeatBox
import com.orinab.shoporinab.utils.network_helper.NetworkHelper
import com.orinab.shoporinab.utils.tools.*
import com.orinab.shoporinab.utils.type_converters.ToStringConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    single { initNavController() }
    single { provideRetrofit(get(), get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> { return@single ApiHelperImpl(get()) }
    single { return@single ThrowableTools(get(), get()) }
    single { return@single Gson() }
    single { return@single ToastTools() }
    single { return@single GlideTools(get(), get()) }
    single { return@single HandleErrorTools() }
    single { return@single GridLayoutCountManager(get()) }
    single { return@single ToStringConverterFactory() }
    single { return@single KeyboardManager() }
    single { return@single NetworkTools() }
    single { return@single BottomSheetDialog(activity) }
    single { return@single ZoomOutSlideTransformer() }
    single { return@single BeatBox(get(), get()) }
    single { return@single MediaPlayer() }
    single { return@single SplitterTools() }
    single { provideOkHttpClient() }
}

private fun initNavController() =
    Navigation.findNavController(activity, R.id.my_nav_fragment)

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

fun provideOkHttpClient(): OkHttpClient {
    val i = Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        val request: Request = requestBuilder.build()
        val response = chain.proceed(request)
        response
    }
    val okHttpClient= OkHttpClient.Builder().writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).addInterceptor(i);
    if (DEBUG) {
        okHttpClient.addInterceptor(ChuckInterceptor(context))
    }
    return okHttpClient.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, toStringConverterFactory: ToStringConverterFactory): ApiService =
    Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(toStringConverterFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
