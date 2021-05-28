package com.msomu.squareissues.ui.screen

import com.msomu.squareissues.data.GithubIssuesItem

/**
 * This data class represents the view state for the home screen.
 */
data class HomeViewState(
    val issueLists : List<GithubIssuesItem> = emptyList()
)