package jp.kaleidot725.githubclient.repository

import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.common.SuspendWrapper
import jp.kaleidot725.githubclient.model.FileItem
import jp.kaleidot725.githubclient.model.GistItem

class GistRepository(private val gistApi: GistApi) {
    suspend fun getGists(userName: String): List<GistItem> {
        return gistApi.getGists(userName).toList().mapNotNull {
            val files = it.gistFiles
            val firstFile = files.firstOrNull() ?: return@mapNotNull null
            val firstFileName = firstFile.filename
            GistItem(id = it.id, name = firstFileName, createdAt = it.createdAt)
        }
    }

    suspend fun getGistFiles(gistId: String): List<FileItem> {
        val gist = gistApi.getGist(gistId)
        return gist.gistFiles.map {
            FileItem(
                name = it.filename,
                language = it.language,
                type = it.type,
                content = it.content
            )
        }
    }

    interface Native {
        fun getGists(userName: String): SuspendWrapper<List<GistItem>>
        fun getGistFiles(gistId: String): SuspendWrapper<List<FileItem>>
    }
}

