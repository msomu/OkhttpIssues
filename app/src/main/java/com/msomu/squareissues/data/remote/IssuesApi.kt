package com.msomu.squareissues.data.remote

import com.msomu.squareissues.data.CommentResponse
import com.msomu.squareissues.data.GithubIssues
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject


class IssuesApi constructor(
    private val httpClient: HttpClient
) {

    suspend fun getIssues(): GithubIssues
    = httpClient.get("square/okhttp/issues?state=all")

    suspend fun getComments(issueNumber: Int): List<CommentResponse> =
        httpClient.get("square/okhttp/issues/$issueNumber/comments")
}