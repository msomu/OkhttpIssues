package com.msomu.squareissues.data

import androidx.room.withTransaction
import com.msomu.squareissues.data.database.IssuesDatabase
import com.msomu.squareissues.data.remote.IssuesApi
import com.msomu.squareissues.util.networkBoundResource
import javax.inject.Inject

class IssueRepository @Inject constructor(private val api: IssuesApi,
                            private val db: IssuesDatabase) {

    private val issuesDao = db.issueDao()

    suspend fun getIssues() = networkBoundResource(
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

    suspend fun fetchIssues() = api.getIssues()
}