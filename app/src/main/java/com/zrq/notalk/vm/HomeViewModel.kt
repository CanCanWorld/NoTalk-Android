package com.zrq.notalk.vm

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zrq.notalk.R
import com.zrq.notalk.entity.BottomItemEntity
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
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val apiService: ApiService
) : ViewModel() {

    val notes by mutableStateOf(mutableStateListOf<NoteItem>())
    var input by mutableStateOf("")

    val bottomItems = listOf(
        BottomItemEntity("首页", R.drawable.baseline_home_24),
        BottomItemEntity("我的", R.drawable.baseline_person_24),
    )
    var currentIndex by mutableStateOf(0)

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


    private companion object {
        const val TAG = "HomeViewModel"
    }
}