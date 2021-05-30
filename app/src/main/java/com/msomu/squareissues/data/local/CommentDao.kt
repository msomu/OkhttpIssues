package com.msomu.squareissues.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msomu.squareissues.data.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments")
    fun getAllComments(): Flow<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<Comment>)

    @Query("DELETE FROM comments")
    suspend fun deleteAllComments()

    @Query("DELETE FROM comments WHERE issueNumber = :issueNumber")
    suspend fun deleteAllComments(issueNumber : Int)

    @Query("SELECT * FROM comments WHERE issueNumber = :issueNumber")
    fun getAllComments(issueNumber : Int): Flow<List<Comment>>
}