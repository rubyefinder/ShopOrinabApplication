package com.orinab.shoporinab.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orinab.shoporinab.data.model.room_db.User
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.USER_DATABASE

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        private var instance: UserDatabase? = null
        fun getInstance(): UserDatabase {
            if (instance == null) {

                instance = Room.databaseBuilder(AppShopOrinab.context.applicationContext,
                    UserDatabase::class.java,
                    USER_DATABASE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }

}