package com.msomu.squareissues.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msomu.squareissues.data.Comment
import com.msomu.squareissues.data.GithubIssuesItem

@Database(entities = [GithubIssuesItem::class, Comment::class], version = 1)
abstract class IssuesDatabase : RoomDatabase() {

    abstract fun issueDao(): IssueDao
    abstract fun commentsDao(): CommentDao
}