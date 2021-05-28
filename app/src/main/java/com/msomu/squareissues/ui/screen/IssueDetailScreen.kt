package com.msomu.squareissues.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun IssueDetailScreen(issueId: Int, navigateBack: () -> Unit) {

}

@Preview(showBackground = true)
@Composable
fun DefaultIssueDetailPreview() {
    SquareOkhttpIssuesTheme {
        IssueDetailScreen(mockIssues()[0].id) {}
    }
}