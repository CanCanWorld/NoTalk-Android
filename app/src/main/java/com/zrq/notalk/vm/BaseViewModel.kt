package com.zrq.notalk.vm

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import com.tencent.mmkv.MMKV
import com.zrq.notalk.activity.BaseActivity
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/2 11:20
 */
open class BaseViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    lateinit var activity: BaseActivity

    protected val mmkv: MMKV = MMKV.defaultMMKV()

}