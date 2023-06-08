package com.zrq.notalk.vm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.zrq.notalk.entity.Image
import com.zrq.notalk.entity.ImageItem
import com.zrq.notalk.network.ApiService
import com.zrq.notalk.utils.Constants.REQUEST_CODE_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.Date
import javax.inject.Inject

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 10:36
 */
@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class PicViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : BaseViewModel() {

    val images by mutableStateOf(mutableStateListOf<ImageItem>())

    fun getList() {
        apiService.getPicList().enqueue(object : Callback<Image> {
            override fun onResponse(call: Call<Image>, response: Response<Image>) {
                val image = response.body()
                if (image != null) {
                    images.clear()
                    images.addAll(image)
                }
            }

            override fun onFailure(call: Call<Image>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun jumpToPhoto() {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(intent, REQUEST_CODE_IMAGE)
    }

    fun uploadImage(imgFile: File) {
        val uid = mmkv.getInt("uid", 0)

        val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imgFile)

        apiService.uploadPic(requestBody, uid).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val result = response.body() ?: false
                Log.d(TAG, "上传结果：$result")
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    companion object {
        private const val TAG = "PicViewModel"
    }

}