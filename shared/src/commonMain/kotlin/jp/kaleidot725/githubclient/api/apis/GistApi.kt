package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.request.*
import jp.kaleidot725.githubclient.api.dto.GistDto

class GistApi(private val manager: HttpClientManager, private val baseUrl: String) {
    suspend fun getGists(): ArrayList<GistDto> {
        return manager.client.get("$baseUrl/gists")
    }
}