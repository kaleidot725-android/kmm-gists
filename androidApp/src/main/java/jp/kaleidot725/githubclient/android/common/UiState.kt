package jp.kaleidot725.githubclient.android.common

sealed class UiState<out T, out E> {
    object Loading : UiState<Nothing, Nothing>()

    data class Success<out T>(val data: T) : UiState<T, Nothing>()

    data class Error<out E>(val error: E) : UiState<Nothing, E>()
}
