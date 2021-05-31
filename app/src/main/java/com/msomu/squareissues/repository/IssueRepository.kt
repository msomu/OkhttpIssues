package com.msomu.squareissues.repository

import com.msomu.squareissues.data.Comment
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.util.Resource
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssue(number : Int) : Flow<GithubIssuesItem>
    fun getIssues() :  Flow<Resource<List<GithubIssuesItem>>>
    fun getComments(issueNumber: Int) : Flow<Resource<List<Comment>>>
}