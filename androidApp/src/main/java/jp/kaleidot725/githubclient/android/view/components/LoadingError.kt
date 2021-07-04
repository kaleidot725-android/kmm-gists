package jp.kaleidot725.githubclient.android.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.resources.Strings
import jp.kaleidot725.githubclient.android.resources.TextStyles

@Composable
fun LoadingError(modifier: Modifier = Modifier, onRetry: (() -> Unit)? = null) {
    Column(modifier = modifier) {
        Text(
            Strings.LOADING_ERROR,
            style = TextStyles.h5,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onRetry?.invoke() }
        ) {
            Text(text = "RETRY", style = TextStyles.button)
        }
    }
}