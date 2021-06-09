package com.msomu.squareissues.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.msomu.squareissues.data.GithubIssuesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDao {

    @Query("SELECT * FROM issues")
    fun getAllIssues(): Flow<List<GithubIssuesItem>>

    @Query("SELECT * FROM issues WHERE number = :number")
    fun getIssue(number : Int): Flow<GithubIssuesItem>

    @Query("SELECT * FROM issues WHERE number = :number")
    fun getIssueSus(number : Int): GithubIssuesItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssues(issues: List<GithubIssuesItem>)

    @Query("DELETE FROM issues")
    suspend fun deleteAllIssues()

    @Update
    suspend fun updateIssue(issue : GithubIssuesItem)
}