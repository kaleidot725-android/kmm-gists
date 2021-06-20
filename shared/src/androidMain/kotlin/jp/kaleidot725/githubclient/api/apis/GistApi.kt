package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.kaleidot725.githubclient.api.dto.GistDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GistApi(private val baseUrl: String) {
    private val client = HttpClient()

    suspend fun getGists(): List<GistDto> {
        val body = callGetMethod("$baseUrl/gists")
        return Json.decodeFromString(body)
    }

    private suspend fun callGetMethod(url: String) : String {
        val response: HttpResponse = client.get("$baseUrl/gists")
        return response.receive()
    }
}