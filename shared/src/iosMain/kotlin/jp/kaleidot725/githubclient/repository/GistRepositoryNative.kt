package jp.kaleidot725.githubclient.repository

import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.dto.GistDto
import jp.kaleidot725.githubclient.common.SuspendWrapper

class GistRepositoryNative(private val gistApi: GistApi) {
    fun getGists(userName: String): SuspendWrapper<List<GistDto>> {
        return SuspendWrapper { gistApi.getGists(userName).toList() }
    }
}