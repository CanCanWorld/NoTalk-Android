package com.zrq.notalk.vm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.activity.ImageDetailActivity
import com.zrq.notalk.entity.Image
import com.zrq.notalk.entity.ImageItem
import com.zrq.notalk.network.ApiService
import com.zrq.notalk.utils.Constants.REQUEST_CODE_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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
    var isRefresh by mutableStateOf(false)

    fun getList() {
        apiService.getPicList().enqueue(object : Callback<Image> {
            override fun onResponse(call: Call<Image>, response: Response<Image>) {
                val image = response.body()
                if (image != null) {
                    images.clear()
                    images.addAll(image)
                    Toast.makeText(application, "刷新成功一共${images.size}条数据", Toast.LENGTH_SHORT).show()
                }
                isRefresh = false
            }

            override fun onFailure(call: Call<Image>, t: Throwable) {
                t.printStackTrace()
                isRefresh = false
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

        apiService.uploadPic(requestBody, uid, Date().time).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val result = response.body() ?: false
                Log.d(TAG, "上传结果：$result")
                if (result){
                    Toast.makeText(application, "上传成功", Toast.LENGTH_SHORT).show()
                    getList()
                }else{
                    Toast.makeText(application, "上传失败，可能文件过大", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(application, "上传失败，服务器出问题了", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun jumpToDetail(path: String) {
        activity.startActivity(Intent(activity, ImageDetailActivity::class.java).apply {
            putExtra("image_path", path)
        })
    }

    companion object {
        private const val TAG = "PicViewModel"
    }

}