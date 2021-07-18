package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.clickable
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
import jp.kaleidot725.githubclient.android.common.TextStyles
import jp.kaleidot725.githubclient.model.GistItem

@Composable
fun GistCard(gist: GistItem, onClicked: ((GistItem) -> Unit)? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable { onClicked?.invoke(gist) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = gist.name, style = TextStyles.h6, color = Color.Black, maxLines = 2)
            Text(
                text = gist.createdAt,
                style = TextStyles.caption,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}