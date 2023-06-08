package com.zrq.notalk.entity

class Image : ArrayList<ImageItem>()

data class ImageItem(
    val id: Int,
    val path: String,
    val time: String,
    val title: String,
    val uid: Int,
    val user: User
)