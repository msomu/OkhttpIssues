package com.msomu.squareissues.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msomu.squareissues.data.User
import com.msomu.squareissues.mock.mockIssueComment
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun IssueItem(user : User, id : Int, title : String?, updatedDate : String, body : String, status : String?, isDetailPage: Boolean, onClick: (Int) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { onClick(id) })) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                UserView(user, updatedDate)
                status?.let { Status(it) }
            }
            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
            Text(
                text = if (isDetailPage) body else body.take(200) + if (body.length > 200) "..." else "",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultIssueItemPreview() {
    SquareOkhttpIssuesTheme {
        val issue = mockIssues()[0]
        IssueItem(
            user = issue.user,
            isDetailPage = true,
            id= issue.number,
            body = issue.body,
            title = issue.title,
            status = issue.state,
            updatedDate = issue.updated_at
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultCommentPreview() {
    SquareOkhttpIssuesTheme {
        val issue = mockIssueComment()
        IssueItem(
            user = issue.user,
            isDetailPage = false,
            id= issue.id,
            body = issue.body,
            title = null,
            status = null,
            updatedDate = issue.updated_at
        ) { }
    }
}
