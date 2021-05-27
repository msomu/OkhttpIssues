package com.msomu.squareissues.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun IssueItem(item: GithubIssuesItem) {
    Card {
        Column {
            Row {
                Icon(imageVector = Icons.Rounded.Warning, contentDescription = item.state)
                Text(text = item.title)
            }
            Text(text = item.body.take(200))
            Row {
                UserView(item.user)
                Text(text = item.updated_at)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultIssueItemPreview() {
    SquareOkhttpIssuesTheme {
        IssueItem(mockIssues()[0])
    }
}
