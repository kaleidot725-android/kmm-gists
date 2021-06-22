package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.kaleidot725.githubclient.api.dto.GistsDto

class GistApi(private val manager: HttpClientManager, private val baseUrl: String) {
    suspend fun getGists(): GistsDto {
        return manager.client.get("$baseUrl/gists")
    }
}