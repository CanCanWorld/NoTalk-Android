package com.zrq.notalk.entity

import androidx.annotation.Keep

@Keep
class Image : ArrayList<ImageItem>()

@Keep
data class ImageItem(
    val id: Int,
    val path: String,
    val time: String,
    val title: String,
    val uid: Int,
    val user: User
)