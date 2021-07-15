package jp.kaleidot725.githubclient.repository

import jp.kaleidot725.githubclient.common.SuspendWrapper
import jp.kaleidot725.githubclient.model.FileItem
import jp.kaleidot725.githubclient.model.GistItem

class GistRepositoryNative(private val gistRepository: GistRepository) {
    fun getGists(userName: String): SuspendWrapper<List<GistItem>> {
        return SuspendWrapper { gistRepository.getGists(userName) }
    }

    fun getGistFiles(gistId: String): SuspendWrapper<List<FileItem>> {
        return SuspendWrapper { gistRepository.getGistFiles(gistId) }
    }
}