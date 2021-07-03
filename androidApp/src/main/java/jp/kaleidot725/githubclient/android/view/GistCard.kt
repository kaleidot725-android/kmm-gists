package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.api.dto.GistDto

@Composable
fun GistCard(gist: GistDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Text(text = gist.gistFiles.firstOrNull()?.filename ?: "EMPTY", color = Color.Black)
    }
}