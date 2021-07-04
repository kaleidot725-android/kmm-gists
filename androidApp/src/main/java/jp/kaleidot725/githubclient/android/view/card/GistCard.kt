package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.api.dto.GistDto

@Composable
fun GistCard(gist: GistDto) {
    val files = gist.gistFiles
    val firstFile = files.firstOrNull() ?: return
    val firstFileName = firstFile.filename
    val createdAt = gist.createdAt

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = firstFileName, style = TextStyles.h6, color = Color.Black, maxLines = 2)
            Text(
                text = createdAt,
                style = TextStyles.caption,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}