package jp.kaleidot725.githubclient.android.view.page

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
import jp.kaleidot725.githubclient.android.common.Strings.DETAILS_PAGE_TITLE
import jp.kaleidot725.githubclient.android.common.TextStyles
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.view.FileCard
import jp.kaleidot725.githubclient.android.view.components.LoadingError
import jp.kaleidot725.githubclient.model.FileItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FilePage(
    fileStateFlow: StateFlow<UiState<List<FileItem>, Unit>>
) {
    val filesState by fileStateFlow.collectAsState()

    Column {

        Box(modifier = Modifier.fillMaxSize()) {
            when (filesState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Error -> {
                    LoadingError(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    LazyColumn() {
                        item {
                            Text(
                                text = DETAILS_PAGE_TITLE,
                                style = TextStyles.h3,
                                color = Color.Black,
                                modifier = Modifier.padding(8.dp)
                            )
                        }

                        items((filesState as UiState.Success<List<FileItem>>).data) { file ->
                            FileCard(file)
                        }
                    }
                }
            }
        }
    }
}