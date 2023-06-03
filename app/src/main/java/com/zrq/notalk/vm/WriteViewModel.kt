package com.zrq.notalk.vm

import android.os.SystemClock
import android.util.Log
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
    private val apiService: ApiService
) : BaseViewModel() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var titleError by mutableStateOf(false)
    var contentError by mutableStateOf(false)

    fun submit() {
        titleError = title.length < 5
        contentError = content.length < 15
        if (titleError || contentError) {
            return
        } else {
            //提交
            val simpleDateFormat = SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA)
            val time = simpleDateFormat.format(SystemClock.currentThreadTimeMillis())
            val uid = mmkv.getInt("uid", 0)
            if (uid != 0) {
                val noteMap = mutableMapOf<String,Any?>()
                noteMap["content"] = content
                noteMap["title"] = title
                noteMap["uid"] = uid.toString()
                noteMap["time"] = time
                apiService.addNote(noteMap).enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (response.body() == true) {
                            Log.d(TAG, "成功")
                        } else {
                            Log.d(TAG, "失败1")
                        }
                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        t.printStackTrace()
                        Log.d(TAG, "失败2")
                    }

                })
            }
        }
    }

    private companion object {
        const val TAG = "NoteViewModel"
    }
}