package com.msomu.squareissues.data.database

import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.data.IssuesRepository
import com.msomu.squareissues.mock.mockIssues

class IssuesDatabase : IssuesRepository {
    override suspend fun getIssues(): List<GithubIssuesItem> {
        return mockIssues()
    }
}