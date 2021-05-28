package com.msomu.squareissues.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun IssueItem(item: GithubIssuesItem) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                UserView(item.user, item.updated_at)
                Status(item.state)
            }
            Text(
                text = item.title,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = item.body.take(200)+ if(item.body.length > 200) "..." else "",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Divider(
                modifier = Modifier.height(3.dp),
                color = MaterialTheme.colors.onPrimary
            )
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