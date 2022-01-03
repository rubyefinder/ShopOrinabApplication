package com.orinab.shoporinab.di

import com.orinab.shoporinab.ui.view.adapter.auto_slider.AutoSliderAdapter
import com.orinab.shoporinab.ui.view.adapter.cabinet_kitchen.CabinetKitchenAdapter
import com.orinab.shoporinab.ui.view.adapter.category.CategoryAdapter
import com.orinab.shoporinab.ui.view.adapter.dashboard.DashboardAdapter
import com.orinab.shoporinab.ui.view.adapter.sub.SubAdapter
import com.orinab.shoporinab.ui.view.adapter.sub_slider_list.SubSliderAdapter
import org.koin.dsl.module

val adapterModule = module {
   single { DashboardAdapter(get(),get(),get()) }
   single { CabinetKitchenAdapter(get(),get()) }
   single { AutoSliderAdapter(get(),get()) }
   single { SubSliderAdapter(get(),get()) }
}