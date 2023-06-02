package com.zrq.notalk.vm

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.activity.HomeActivity
import com.zrq.notalk.entity.User
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : BaseViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var showLoadingDialog by mutableStateOf(false)

    init {
        username = mmkv.getString("username", "").toString()
        password = mmkv.getString("password", "").toString()
        if (username != "" && password != "") {
            login()
        }
    }

    fun login() {
        Log.d(TAG, "登录...")
        showLoadingDialog = true
        apiService.login(username, password).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                showLoadingDialog = false
                if (user != null) {
                    mmkv.putString("username", user.username)
                    mmkv.putString("password", user.password)
                    mmkv.putInt("uid", user.id)
                    Toast.makeText(application, "登录成功", Toast.LENGTH_SHORT).show()
                    activity.startActivity(Intent(activity, HomeActivity::class.java))
                    activity.finish()
                } else {
                    Toast.makeText(application, "登录失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
                showLoadingDialog = false
                Toast.makeText(application, "登录失败", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private companion object {
        const val TAG = "LoginViewModel"
    }
}