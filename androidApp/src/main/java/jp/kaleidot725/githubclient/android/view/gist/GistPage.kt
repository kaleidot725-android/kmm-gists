package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.githubclient.android.common.Strings.MAIN_PAGE_TITLE
import jp.kaleidot725.githubclient.android.common.TextStyles
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.view.components.LoadingError
import jp.kaleidot725.githubclient.model.GistItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GistPage(
    gistStatusFlow: StateFlow<UiState<List<GistItem>, Unit>>,
    onClickedGist: ((GistItem) -> Unit)? = null,
    onFetchedGist: (() -> Unit)? = null
) {
    val gistStatus by gistStatusFlow.collectAsState()

    Column {
        Box(modifier = Modifier.fillMaxSize()) {
            when (gistStatus) {
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
                    LazyColumn() {
                        item {
                            Text(
                                text = MAIN_PAGE_TITLE,
                                style = TextStyles.h3,
                                color = Color.Black,
                                modifier = Modifier.padding(8.dp)
                            )
                        }

                        items((gistStatus as UiState.Success<List<GistItem>>).data) { gist ->
                            GistCard(gist, onClickedGist)
                        }
                    }
                }
            }
        }
    }
}