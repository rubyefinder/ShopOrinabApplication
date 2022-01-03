package com.orinab.shoporinab.utils.extention

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.orinab.shoporinab.R
import saschpe.android.customtabs.CustomTabsHelper.Companion.addKeepAliveExtra
import saschpe.android.customtabs.CustomTabsHelper.Companion.openCustomTab
import saschpe.android.customtabs.WebViewFallback


fun intentChrome(context: Context, url:String){
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_close)
    val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder()
        .addDefaultShareMenuItem()
        .setToolbarColor(context.resources.getColor(R.color.primary_dark))
        .setShowTitle(true)
        .setCloseButtonIcon(bitmap)
        .build()
    addKeepAliveExtra(context, customTabsIntent.intent)
    openCustomTab(
        context, customTabsIntent,
        Uri.parse(url),
        WebViewFallback()
    )
}