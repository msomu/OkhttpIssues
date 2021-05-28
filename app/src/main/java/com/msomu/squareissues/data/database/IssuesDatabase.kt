package com.msomu.squareissues.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msomu.squareissues.data.GithubIssuesItem

@Database(entities = [GithubIssuesItem::class], version = 1)
abstract class IssuesDatabase : RoomDatabase() {

    abstract fun issueDao(): IssueDao
}