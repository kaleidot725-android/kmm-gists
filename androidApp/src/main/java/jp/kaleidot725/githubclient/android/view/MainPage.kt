package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.android.resources.TextStyles
import jp.kaleidot725.githubclient.android.viewmodel.MainViewModel
import jp.kaleidot725.githubclient.api.dto.GistDto
import org.koin.androidx.compose.getViewModel

@Composable
fun MainPage(viewModel: MainViewModel = getViewModel()) {
    val gists by viewModel.gists.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (gists) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is UiState.Error -> {
                Text(
                    "Loading Error",
                    style = TextStyles.h4,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is UiState.Success -> {
                LazyColumn {
                    items(
                        (gists as UiState.Success<List<GistDto>>).data,
                        itemContent = { gist -> GistCard(gist) }
                    )
                }
            }
        }
    }
}