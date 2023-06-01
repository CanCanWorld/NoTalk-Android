package com.zrq.notalk.entity

import androidx.annotation.DrawableRes

data class MineItemEntity(
    val title: String,
    @DrawableRes
    val icon: Int,
    val onClick: () -> Unit
)