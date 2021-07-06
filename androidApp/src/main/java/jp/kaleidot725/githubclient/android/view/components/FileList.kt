package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.model.FileItem

@Composable
fun FileList(
    files: List<FileItem>, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(files, itemContent = { file -> FileCard(file) })
    }
}

@Composable
private fun FileCard(file: FileItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = file.name,
                style = TextStyles.h6,
                color = Color.White,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.Magenta)
                    .padding(8.dp)
            )

            Text(
                text = file.content,
                style = TextStyles.caption,
                color = Color.DarkGray,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
            )
        }
    }
}