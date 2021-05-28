package com.msomu.squareissues.data

interface IssuesRepository {
    suspend fun getIssues() : List<GithubIssuesItem>
}