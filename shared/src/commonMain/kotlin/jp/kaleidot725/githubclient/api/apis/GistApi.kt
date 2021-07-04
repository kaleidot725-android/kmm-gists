package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.request.*
import jp.kaleidot725.githubclient.api.dto.GistDto

class GistApi(private val manager: HttpClientManager, private val baseUrl: String) {
    suspend fun getGists(userName: String): Array<GistDto> {
        return manager.client.get("$baseUrl/$userName/gists")
    }

    suspend fun getGist(userName: String, id: String): GistDto {
        return manager.client.get("$baseUrl/$userName/gists/$id")
    }
}