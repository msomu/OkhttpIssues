package com.msomu.squareissues.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

class CommentResponse(
    val body: String,
    val updated_at: String,
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
class Comment(
    val issueNumber: Int,
    val body: String,
    val updated_at: String,
    @Embedded(prefix = "user_")
    val user: User,
    @PrimaryKey val id: Int,
)

fun CommentResponse.toComment(issueNumber: Int) : Comment{
    return Comment(
        issueNumber = issueNumber,
        body = body,
        updated_at = updated_at,
        user = user,
        id = id
    )
}