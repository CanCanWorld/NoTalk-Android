package com.zrq.notalk.vm

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zrq.notalk.R
import com.zrq.notalk.activity.LoginActivity
import com.zrq.notalk.entity.MineItemEntity

class MineViewModel : BaseViewModel() {
    val mineItem1 = listOf(
        MineItemEntity("关于我们", R.drawable.baseline_account_circle_24) {},
        MineItemEntity("联系我们", R.drawable.baseline_call_24) {},
        MineItemEntity("使用帮助", R.drawable.baseline_help_24) {},
        MineItemEntity("问题反馈", R.drawable.baseline_question_answer_24) {},
        MineItemEntity("修改密码", R.drawable.baseline_drive_file_rename_outline_24) {
            isShowPasswordDialog = true
        },
    )
    val mineItem2 = listOf(
        MineItemEntity("检查更新", R.drawable.baseline_update_24) {},
        MineItemEntity("退出登录", R.drawable.baseline_exit_to_app_24) {
            mmkv.remove("username")
            mmkv.remove("password")
            mmkv.remove("uid")
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        },
    )

    var username by mutableStateOf("")
    var uid by mutableStateOf("")
    var isShowPasswordDialog by mutableStateOf(false)
    var passwordInput by mutableStateOf("")
    var isPasswordError by mutableStateOf(false)

    init {
        username = mmkv.getString("username", "").toString()
        uid = mmkv.getInt("uid", -1).toString()
        Log.d(TAG, "uid: $uid")
    }

    fun changePassword() {
        isPasswordError = passwordInput.length < 6
    }

    private companion object {
        const val TAG = "MineWidgetVM"
    }
}