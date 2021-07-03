package jp.kaleidot725.githubclient.android.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import jp.kaleidot725.githubclient.android.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun App(viewModel: MainViewModel = getViewModel()) {
    val gists by viewModel.gists.collectAsState()

    LazyColumn {
        items(gists, itemContent = { gist -> GistCard(gist) })
    }
}