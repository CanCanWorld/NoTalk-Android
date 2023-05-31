package com.zrq.notalk.entity

data class NoteItem(
    val content: String,
    val title: String,
    val id: Int,
    val time: String,
    val uid: Int,
    val user: User
)