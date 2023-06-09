package com.zrq.notalk.entity

import androidx.annotation.Keep

@Keep
data class User(
    val id: Int,
    val password: String,
    val username: String
)