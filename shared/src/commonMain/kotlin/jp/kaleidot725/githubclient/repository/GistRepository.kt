package jp.kaleidot725.githubclient.repository

import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.dto.GistDto

class GistRepository(private val gistApi: GistApi) {
    suspend fun getGists(userName: String): List<GistDto> {
        return gistApi.getGists(userName)
    }
}