package com.zrq.notalk.vm

import android.app.Application
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class WriteViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : BaseViewModel() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var titleError by mutableStateOf(false)
    var contentError by mutableStateOf(false)

    fun submit() {
        titleError = title.length < 2
        contentError = content.length < 5
        if (titleError || contentError) {
            return
        } else {
            //提交
            val simpleDateFormat = SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA)
            val time = simpleDateFormat.format(SystemClock.currentThreadTimeMillis())
            val uid = mmkv.getInt("uid", 0)
            if (uid != 0) {
                val noteMap = mutableMapOf<String, Any?>()
                noteMap["content"] = content
                noteMap["title"] = title
                noteMap["uid"] = uid.toString()
                noteMap["time"] = time
                apiService.addNote(noteMap).enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (response.body() == true) {
                            Log.d(TAG, "成功")
                            Toast.makeText(application, "上传成功", Toast.LENGTH_SHORT).show()
                            activity.finish()
                        } else {
                            Log.d(TAG, "失败1")
                            Toast.makeText(application, "上传失败，请稍后重试", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        t.printStackTrace()
                        Log.d(TAG, "失败2")
                        Toast.makeText(application, "上传失败，服务器出问题了", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }

    private companion object {
        const val TAG = "NoteViewModel"
    }
}