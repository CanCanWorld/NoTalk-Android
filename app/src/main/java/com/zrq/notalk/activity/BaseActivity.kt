package com.zrq.notalk.activity

import androidx.activity.ComponentActivity
import com.zrq.notalk.vm.BaseViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/2 11:18
 */
abstract class BaseActivity : ComponentActivity() {

    fun initViewModel(vm: BaseViewModel) {
        vm.activity = this
    }

}