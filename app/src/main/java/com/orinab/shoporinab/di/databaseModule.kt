package com.orinab.shoporinab.di

import com.orinab.shoporinab.data.room.UserDatabase
import org.koin.dsl.module


val DatabaseModule = module {
    single { UserDatabase.getInstance() }
}