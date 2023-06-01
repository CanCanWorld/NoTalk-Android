package com.zrq.notalk.vm

import androidx.lifecycle.ViewModel
import com.zrq.notalk.R
import com.zrq.notalk.entity.MineItemEntity

class MineWidgetVM : ViewModel() {
    //data
    val mineItem1 = listOf(
        MineItemEntity("关于我们", R.drawable.baseline_account_circle_24) {},
        MineItemEntity("联系我们", R.drawable.baseline_call_24) {},
        MineItemEntity("使用帮助", R.drawable.baseline_help_24) {},
        MineItemEntity("问题反馈", R.drawable.baseline_question_answer_24) {},
        MineItemEntity("修改密码", R.drawable.baseline_drive_file_rename_outline_24) {},
    )
    val mineItem2 = listOf(
        MineItemEntity("检查更新", R.drawable.baseline_update_24) {},
        MineItemEntity("退出登录", R.drawable.baseline_exit_to_app_24) {},
    )

    //methods


    //const
    private companion object{
        const val TAG = "MineWidgetVM"
    }
}