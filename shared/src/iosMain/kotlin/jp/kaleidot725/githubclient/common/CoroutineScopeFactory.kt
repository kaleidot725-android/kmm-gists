package jp.kaleidot725.githubclient.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class CoroutineScopeFactory {
    fun create(): CoroutineScope {
        return MainScope()
    }
}