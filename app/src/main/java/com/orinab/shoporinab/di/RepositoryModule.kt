package com.orinab.shoporinab.di

import com.orinab.shoporinab.data.repository.*
import org.koin.dsl.module

val repoModule = module {

    single { DashboardRepository(get()) }
    single { UserRepository(get()) }

}