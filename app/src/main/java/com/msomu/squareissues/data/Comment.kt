package com.msomu.squareissues.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CommentResponse(
    val body: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val user: User,
    val id: Int
)

@Entity(
    tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = GithubIssuesItem::class,
        parentColumns = arrayOf("number"),
        childColumns = arrayOf("issueNumber"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Comment(
    val issueNumber: Int,
    val body: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @Embedded(prefix = "user_")
    val user: User,
    @PrimaryKey val id: Int,
)

fun CommentResponse.toComment(issueNumber: Int) : Comment{
    return Comment(
        issueNumber = issueNumber,
        body = body,
        updatedAt = updatedAt,
        user = user,
        id = id
    )
}