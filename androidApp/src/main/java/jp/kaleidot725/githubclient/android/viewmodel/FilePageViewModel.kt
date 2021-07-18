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

class FilePageViewModel(private val gistRepository: GistRepository) : ViewModel() {
    private val _filestatus = MutableStateFlow<UiState<List<FileItem>, Unit>>(UiState.Loading)
    val fileStatus: StateFlow<UiState<List<FileItem>, Unit>> = _filestatus

    fun fetchFiles(gistId: String) {
        viewModelScope.launch {
            _filestatus.value = UiState.Loading

            withContext(Dispatchers.IO) {
                try {
                    _filestatus.value = UiState.Success(
                        gistRepository.getGistFiles(gistId)
                    )
                } catch (e: Exception) {
                    _filestatus.value = UiState.Error(Unit)
                }
            }
        }
    }
}