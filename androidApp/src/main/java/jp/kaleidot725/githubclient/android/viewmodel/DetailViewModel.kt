package jp.kaleidot725.githubclient.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.api.dto.FileDto
import jp.kaleidot725.githubclient.repository.GistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val gistRepository: GistRepository) : ViewModel() {
    private val _files = MutableStateFlow<UiState<List<FileDto>, Unit>>(UiState.Loading)
    val files: StateFlow<UiState<List<FileDto>, Unit>> = _files

    fun fetchFiles() {
        viewModelScope.launch {
            _files.value = UiState.Loading
            withContext(Dispatchers.IO) {
            }
        }
    }
}