package com.orinab.shoporinab.ui.view.activity

import android.os.Bundle
import android.view.View
import com.orinab.shoporinab.R
import com.orinab.shoporinab.utils.application.AppShopOrinab.Companion.activity
import com.orinab.shoporinab.utils.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity =this
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }
}