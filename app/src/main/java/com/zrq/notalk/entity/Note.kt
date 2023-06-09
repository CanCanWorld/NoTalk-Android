package com.zrq.notalk.entity

import androidx.annotation.Keep

@Keep
class Note : ArrayList<NoteItem>()

@Keep
data class NoteItem(
    val content: String,
    val title: String,
    val id: Int,
    val time: String,
    val uid: Int,
    val user: User?
)