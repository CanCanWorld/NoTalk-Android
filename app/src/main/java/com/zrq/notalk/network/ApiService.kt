package com.zrq.notalk.network

import com.zrq.notalk.entity.Note
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/5/30 15:31
 */
interface ApiService {

    @GET("/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<Boolean>

    @GET("/note")
    fun queryAllNotes(): Call<Note>
}