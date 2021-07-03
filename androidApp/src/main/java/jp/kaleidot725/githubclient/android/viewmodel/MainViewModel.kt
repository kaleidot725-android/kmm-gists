package jp.kaleidot725.githubclient.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.githubclient.api.dto.GistDto
import jp.kaleidot725.githubclient.repository.GistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val gistRepository: GistRepository) : ViewModel() {
    private val _gists: MutableStateFlow<List<GistDto>> = MutableStateFlow(emptyList())
    val gists: StateFlow<List<GistDto>> = _gists

    init {
        fetchGists()
    }

    fun fetchGists() {
        viewModelScope.launch {
            _gists.value = gistRepository.getGists("kaleidot725")
        }
    }
}