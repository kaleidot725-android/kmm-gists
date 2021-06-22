package jp.kaleidot725.githubclient.api.apis

import io.ktor.client.*

expect class HttpClientManager() {
    val client: HttpClient
}