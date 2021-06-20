package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.*

expect class GistApiClient() {
    val client: HttpClient
}