package com.orinab.shoporinab.di

import com.orinab.shoporinab.ui.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { UserViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
}