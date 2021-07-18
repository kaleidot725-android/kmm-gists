package jp.kaleidot725.githubclient.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.githubclient.android.common.Strings.TEST_USER
import jp.kaleidot725.githubclient.android.common.UiState
import jp.kaleidot725.githubclient.model.GistItem
import jp.kaleidot725.githubclient.repository.GistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GistPageViewModel(private val gistRepository: GistRepository) : ViewModel() {
    private val _gistStatus = MutableStateFlow<UiState<List<GistItem>, Unit>>(UiState.Loading)
    val gistStatus: StateFlow<UiState<List<GistItem>, Unit>> = _gistStatus

    fun fetchGists() {
        viewModelScope.launch {
            _gistStatus.value = UiState.Loading

            withContext(Dispatchers.IO) {
                try {
                    _gistStatus.value = UiState.Success(gistRepository.getGists(TEST_USER))
                } catch (e: Exception) {
                    _gistStatus.value = UiState.Error(Unit)
                }
            }
        }
    }
}