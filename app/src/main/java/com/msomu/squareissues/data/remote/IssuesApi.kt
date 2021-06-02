package com.msomu.squareissues.data.remote

import com.msomu.squareissues.data.CommentResponse
import com.msomu.squareissues.data.GithubIssues
import retrofit2.http.GET
import retrofit2.http.Path


interface IssuesApi {

    companion object {
        const val BASE_URL = "https://api.github.com/repos/"
    }

    @GET("square/okhttp/issues?state=all")
    suspend fun getIssues(): GithubIssues

    @GET("square/okhttp/issues/{issueNumber}/comments")
    suspend fun getComments(@Path("issueNumber") issueNumber : Int): List<CommentResponse>
}