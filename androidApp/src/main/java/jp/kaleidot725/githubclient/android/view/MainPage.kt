package jp.kaleidot725.githubclient.android.view

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
import jp.kaleidot725.githubclient.android.resources.Strings.MAIN_PAGE_TITLE
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.android.view.components.LoadingError
import jp.kaleidot725.githubclient.api.dto.GistDto
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainPage(
    gistsFlow: StateFlow<UiState<List<GistDto>, Unit>>,
    onClickedGist: ((GistDto) -> Unit)? = null,
    onFetchedGist: (() -> Unit)? = null
) {
    val gists by gistsFlow.collectAsState()

    Column {
        Text(
            text = MAIN_PAGE_TITLE,
            style = TextStyles.h3,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (gists) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Error -> {
                    LoadingError(
                        modifier = Modifier.align(Alignment.Center),
                        onRetry = onFetchedGist
                    )
                }
                is UiState.Success -> {
                    GistList(
                        gists = (gists as UiState.Success<List<GistDto>>).data,
                        onClicked = onClickedGist
                    )
                }
            }
        }
    }
}