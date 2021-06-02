package com.msomu.squareissues.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class GithubIssuesItem(
    val title: String,
    val body: String,
    @Embedded(prefix = "user_")
    val user: User,
    val updated_at: String,
    val state: String,
    @PrimaryKey val number: Int,
    val comments_url: String,
)