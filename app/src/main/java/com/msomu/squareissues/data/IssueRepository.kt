package com.msomu.squareissues.data

import androidx.room.withTransaction
import com.msomu.squareissues.data.local.IssuesDatabase
import com.msomu.squareissues.data.remote.IssuesApi
import com.msomu.squareissues.util.networkBoundResource
import javax.inject.Inject

class IssueRepository @Inject constructor(
    private val api: IssuesApi,
    private val db: IssuesDatabase
) {

    private val issuesDao = db.issueDao()
    private val commentsDao = db.commentDao()

    fun getIssue(number : Int) = issuesDao.getIssue(number)

    fun getIssues() = networkBoundResource(
        query = {
            issuesDao.getAllIssues()
        },
        fetch = {
            api.getIssues()
        },
        saveFetchResult = { issues ->
            db.withTransaction {
                issuesDao.deleteAllIssues()
                issuesDao.insertIssues(issues)
            }
        },
    )

    fun getComments(issueNumber: Int) = networkBoundResource(
        query = {
            commentsDao.getAllComments(issueNumber)
        },
        fetch = {
            api.getComments(issueNumber)
        },
        saveFetchResult = { commentResponse ->
            db.withTransaction {
                commentsDao.deleteAllComments(issueNumber)
                commentsDao.insertComments(commentResponse.map { it.toComment(issueNumber) })
            }
        }
    )
}