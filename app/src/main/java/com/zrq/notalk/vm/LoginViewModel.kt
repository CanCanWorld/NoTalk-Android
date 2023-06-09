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
    var isUsernameError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)

    init {
        username = mmkv.getString("username", "").toString()
        password = mmkv.getString("password", "").toString()
        if (username != "" && password != "") {
            login()
        }
    }

    fun login() {
        Log.d(TAG, "登录...")
        username = username.trim().replace("\n", "")
        password = password.trim().replace("\n", "")
        if (username.length < 6) {
            isUsernameError = true
            return
        }else{
            isUsernameError = false
        }
        if (password.length < 6) {
            isPasswordError = true
            return
        }else{
            isPasswordError = false
        }
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
                Toast.makeText(application, "登录失败，请检查账号密码", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun register() {
        Log.d(TAG, "注册...")
        username = username.trim().replace("\n", "")
        password = password.trim().replace("\n", "")
        if (username.length < 6) {
            isUsernameError = true
            return
        }else{
            isUsernameError = false
        }
        if (password.length < 6) {
            isPasswordError = true
            return
        }else{
            isPasswordError = false
        }
        showLoadingDialog = true
        val noteMap = mutableMapOf<String, Any?>()
        noteMap["username"] = username
        noteMap["password"] = password
        apiService.register(noteMap).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val result = response.body() ?: -1
                showLoadingDialog = false
                if (result > 0) {
                    mmkv.putString("username", username)
                    mmkv.putString("password", password)
                    mmkv.putInt("uid", result)
                    Toast.makeText(application, "注册成功", Toast.LENGTH_SHORT).show()
                    activity.startActivity(Intent(activity, HomeActivity::class.java))
                    activity.finish()
                } else {
                    Toast.makeText(application, "注册失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                t.printStackTrace()
                showLoadingDialog = false
                Toast.makeText(application, "注册失败，服务器出问题了", Toast.LENGTH_SHORT).show()
            }

        })
    }


    private companion object {
        const val TAG = "LoginViewModel"
    }
}