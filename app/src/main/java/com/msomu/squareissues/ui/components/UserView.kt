package com.msomu.squareissues.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.msomu.squareissues.data.User
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun UserView(user: User) {
    Row {
        Image(
            painter = rememberCoilPainter(request = user.avatar_url),
            contentDescription = user.login,
            modifier = Modifier.size(75.dp)
        )
        Text(text = user.login)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultUserPreview() {
    SquareOkhttpIssuesTheme {
        UserView(mockIssues()[0].user)
    }
}