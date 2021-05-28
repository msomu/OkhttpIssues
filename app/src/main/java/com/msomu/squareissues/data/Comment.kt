package com.msomu.squareissues.data

import androidx.room.Embedded

class Comment(
    val body : String,
    val updated_at: String,
    @Embedded(prefix = "user_")
    val user : User,
    val id : Int
)