package com.zrq.notalk.network

import com.zrq.notalk.entity.Note
import com.zrq.notalk.entity.NoteItem
import com.zrq.notalk.entity.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/5/30 15:31
 */
interface ApiService {

    @GET("/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<User>

    @GET("/note")
    fun queryAllNotes(): Call<Note>

    @FormUrlEncoded
    @POST("/note")
    fun addNote(@QueryMap map: Map<String, Any?>): Call<Boolean>
}