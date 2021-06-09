package com.msomu.squareissues.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "issues")
data class GithubIssuesItem(
    val title: String,
    val body: String,
    @Embedded(prefix = "user_")
    val user: User,
    @SerializedName("updated_at")
    val updatedAt: String,
    val state: String,
    @PrimaryKey val number: Int,
    @SerializedName("comments_url")
    val commentsUrl: String,
    var stared : Boolean = false
)

data class IssueData(
    val user: User,
    val id: Int,
    val title: String?,
    val updatedDate: String,
    val body: String,
    val status: String?
)