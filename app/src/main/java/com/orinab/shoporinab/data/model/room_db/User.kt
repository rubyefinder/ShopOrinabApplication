package com.orinab.shoporinab.data.model.room_db


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.USER_ENTITY

@Entity(tableName = USER_ENTITY)
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    @NonNull
    var ids: Int = -1

    @SerializedName("token")
    @ColumnInfo(name = "token")
    @NonNull
    var token: String = ""
}