package com.zrq.notalk.network

import com.zrq.notalk.entity.Image
import com.zrq.notalk.entity.Note
import com.zrq.notalk.entity.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

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
    fun addNote(@FieldMap map: Map<String, @JvmSuppressWildcards Any?>): Call<Boolean>

    @GET("/img")
    fun getPicList(): Call<Image>

    @Multipart
    @POST("/img")
    fun uploadPic(@Part("file") requestBody: RequestBody, @Part("uid") uid: Int): Call<Boolean>
}