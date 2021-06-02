package com.msomu.squareissues.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun Status(state: String) {
    var icon = Icons.Rounded.Info
    var status = "Open"
    var color = Color.Green

    if (state == "open") {
        icon = Icons.Rounded.Info
        status = "Open"
        color = Color.Green
    } else if (state == "closed") {
        icon = Icons.Rounded.Close
        status = "Closed"
        color = Color.Red
    }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = status,
            modifier = Modifier.padding(end = 8.dp),
            tint = color
        )
        Text(status, style = MaterialTheme.typography.h4)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultStatusPreview() {
    SquareOkhttpIssuesTheme {
        Column {
            Status("open")
            Status("closed")
        }
    }
}