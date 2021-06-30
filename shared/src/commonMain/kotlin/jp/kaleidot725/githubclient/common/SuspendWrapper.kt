package jp.kaleidot725.githubclient.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SuspendWrapper<T>(private val func: suspend () -> T) {
    fun subscribe(
        scope: CoroutineScope,
        onSuccess: (item: T) -> Unit,
        onThrow: (error: Throwable) -> Unit
    ): Job {
        return scope.launch {
            try {
                onSuccess(func())
            } catch (error: Throwable) {
                onThrow(error)
            }
        }
    }
}