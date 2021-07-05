package jp.kaleidot725.githubclient.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.model.FileItem
import jp.kaleidot725.githubclient.repository.GistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val gistRepository: GistRepository) : ViewModel() {
    private val _files = MutableStateFlow<UiState<List<FileItem>, Unit>>(UiState.Loading)
    val files: StateFlow<UiState<List<FileItem>, Unit>> = _files

    fun fetchFiles(gistId: String) {
        viewModelScope.launch {
            _files.value = UiState.Loading

            withContext(Dispatchers.IO) {
                try {
                    _files.value = UiState.Success(
                        gistRepository.getGistFiles(gistId)
                    )
                } catch (e: Exception) {
                    _files.value = UiState.Error(Unit)
                }
            }
        }
    }
}