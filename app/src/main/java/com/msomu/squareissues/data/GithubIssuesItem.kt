package com.msomu.squareissues.data

data class GithubIssuesItem(
    val id: Int,
    val title: String,
    val body: String,
    val user: User,
    val updated_at: String,
    val state: String
)