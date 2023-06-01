package com.zrq.notalk

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/5/30 16:23
 */
@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}