package com.msomu.squareissues.ui

import androidx.navigation.NavHostController
import com.msomu.squareissues.ui.Destinations.IssueDetail

object Destinations {
    const val Home = "home"
    const val IssueDetail = "issueDetail"

    object TaskDetailArgs {
        const val IssueId = "issueId"
    }
}

class Actions(navController: NavHostController) {
    val openIssue: (Int) -> Unit = { issueId ->
        navController.navigate("$IssueDetail/$issueId")
    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }
}