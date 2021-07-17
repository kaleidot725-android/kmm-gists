package jp.kaleidot725.githubclient.repository

import jp.kaleidot725.githubclient.common.SuspendWrapper
import jp.kaleidot725.githubclient.model.FileItem
import jp.kaleidot725.githubclient.model.GistItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GistRepositoryNative(private val gistRepository: GistRepository) {
    fun getGists(userName: String): SuspendWrapper<List<GistItem>> {
        return SuspendWrapper {
            withContext(Dispatchers.Default) {
                gistRepository.getGists(userName)
            }
        }
    }

    fun getGistFiles(gistId: String): SuspendWrapper<List<FileItem>> {
        return SuspendWrapper {
            withContext(Dispatchers.Default) {
                gistRepository.getGistFiles(gistId)
            }
        }
    }
}