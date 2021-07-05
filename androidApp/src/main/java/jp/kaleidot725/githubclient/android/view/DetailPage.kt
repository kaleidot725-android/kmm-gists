package jp.kaleidot725.githubclient.android.view.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.resources.Strings.DETAILS_PAGE_TITLE
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.android.view.FileList
import jp.kaleidot725.githubclient.android.view.components.LoadingError
import jp.kaleidot725.githubclient.model.FileItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailPage(
    filesFlow: StateFlow<UiState<List<FileItem>, Unit>>
) {
    val files by filesFlow.collectAsState()

    Column {
        Text(
            text = DETAILS_PAGE_TITLE,
            style = TextStyles.h3,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (files) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Error -> {
                    LoadingError(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    FileList(
                        files = (files as UiState.Success<List<FileItem>>).data,
                        onClicked = { /** TODO */ },
                    )
                }
            }
        }
    }
}