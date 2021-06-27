package jp.kaleidot725.githubclient.api.apis

import com.futuremind.koru.ToNativeClass
import io.ktor.client.request.*
import jp.kaleidot725.githubclient.api.dto.GistDto

@ToNativeClass(name = "GistApiNative")
class GistApi(private val manager: HttpClientManager, private val baseUrl: String) {
    suspend fun getGists(userName: String): Array<GistDto> {
        return manager.client.get<Array<GistDto>>("$baseUrl/$userName/gists")
    }
}