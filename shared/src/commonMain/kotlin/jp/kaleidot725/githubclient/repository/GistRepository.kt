package jp.kaleidot725.githubclient.repository

import com.futuremind.koru.ToNativeClass
import jp.kaleidot725.githubclient.api.apis.GistApi
import jp.kaleidot725.githubclient.api.dto.GistDto

@ToNativeClass(name = "GistRepositoryNative")
class GistRepository(private val gistApi: GistApi) {
    suspend fun getGists(userName: String): List<GistDto> {
        return gistApi.getGists(userName).toList()
    }
}