package com.msomu.squareissues.repository

import androidx.room.withTransaction
import com.msomu.squareissues.data.local.CommentDao
import com.msomu.squareissues.data.local.IssueDao
import com.msomu.squareissues.data.local.IssuesDatabase
import com.msomu.squareissues.data.remote.IssuesApi
import com.msomu.squareissues.data.toComment
import com.msomu.squareissues.util.networkBoundResource
import javax.inject.Inject

class DefaultIssueRepository @Inject constructor(
    private val api: IssuesApi,
    private val db : IssuesDatabase,
    private val issueDao: IssueDao,
    private val commentDao: CommentDao
) : IssueRepository {

    override fun getIssue(number : Int) = issueDao.getIssue(number)

    override fun getIssues() = networkBoundResource(
        query = {
            issueDao.getAllIssues()
        },
        fetch = {
            api.getIssues()
        },
        saveFetchResult = { issues ->
            db.withTransaction {
                issueDao.deleteAllIssues()
                issueDao.insertIssues(issues)
            }
        },
    )

    override fun getComments(issueNumber: Int) = networkBoundResource(
        query = {
            commentDao.getAllComments(issueNumber)
        },
        fetch = {
            api.getComments(issueNumber)
        },
        saveFetchResult = { commentResponse ->
            db.withTransaction {
                commentDao.deleteAllComments(issueNumber)
                commentDao.insertComments(commentResponse.map { it.toComment(issueNumber) })
            }
        }
    )

    override suspend fun starItem(starItemId: Int) {
        val issue = issueDao.getIssueSus(starItemId)
        issue.stared = !issue.stared
        issueDao.updateIssue(issue)
    }
}