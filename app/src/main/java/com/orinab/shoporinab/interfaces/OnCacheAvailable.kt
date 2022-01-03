package com.orinab.shoporinab.interfaces

import java.io.File


interface OnCacheAvailable {
    fun onCacheAvailable(cacheFile: File,url:String,percentsAvailable:Int)
}