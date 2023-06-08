package com.zrq.notalk.vm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.activity.WriteActivity
import com.zrq.notalk.entity.Note
import com.zrq.notalk.entity.NoteItem
import com.zrq.notalk.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/5/30 16:20
 */
@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class NoteViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

    val notes by mutableStateOf(mutableStateListOf<NoteItem>())
    var input by mutableStateOf("")

    fun loadNotes(result: (Boolean) -> Unit) {
        apiService.queryAllNotes().enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                val note = response.body()
                if (note != null) {
                    notes.clear()
                    notes.addAll(note)
                    result(true)
                } else {
                    result(false)
                }
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun navToNote() {
        activity.startActivity(Intent(activity, WriteActivity::class.java))
    }


    private companion object {
        const val TAG = "HomeViewModel"
    }
}