package com.zrq.notalk.vm

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/9 15:11
 */
@HiltViewModel
class ImageViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : BaseViewModel()  {

    var imagePath by mutableStateOf("")

}