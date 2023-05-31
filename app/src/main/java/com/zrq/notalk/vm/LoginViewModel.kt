package com.zrq.notalk.vm

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun login(result: (Boolean) -> Unit) {
        Log.d(TAG, "登录...")
        apiService.login("zrq", "123456").enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val result = response.body()
                if (result != null) {
                    result(result)
                } else {
                    result(false)
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                t.printStackTrace()
                result(false)
            }
        })
    }


    private companion object {
        const val TAG = "LoginViewModel"
    }
}