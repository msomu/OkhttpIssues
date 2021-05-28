package com.msomu.squareissues.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.msomu.squareissues.R
import com.msomu.squareissues.data.User
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme
import com.msomu.squareissues.util.toDate

@Composable
fun UserView(user: User, date : String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberCoilPainter(
                request = user.avatar_url,
                previewPlaceholder = R.drawable.placeholder,
            ),
            contentDescription = user.login,
            modifier = Modifier
                .size(75.dp)
                .padding(end = 16.dp)
                .clip(CircleShape)
        )
        Column {
            Text(text = user.login, style= MaterialTheme.typography.subtitle1)
            Text(text = date.toDate(), style= MaterialTheme.typography.body1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultUserPreview() {
    SquareOkhttpIssuesTheme {
        UserView(mockIssues()[0].user, "2021-05-27T00:31:47Z")
    }
}