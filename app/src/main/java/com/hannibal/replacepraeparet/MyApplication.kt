package com.hannibal.replacepraeparet

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        var instance: MyApplication? = null

        fun applicationContext(): Context? {
            return instance!!.applicationContext
        }
    }
}