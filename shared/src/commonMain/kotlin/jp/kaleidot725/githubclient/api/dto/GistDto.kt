package jp.kaleidot725.githubclient.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class GistDto(
    @SerialName("comments")
    val comments: Int,
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commits_url")
    val commitsUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("forks_url")
    val forksUrl: String,
    @SerialName("git_pull_url")
    val gitPullUrl: String,
    @SerialName("git_push_url")
    val gitPushUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("owner")
    val owner: OwnerDto,
    @SerialName("public")
    val `public`: Boolean,
    @SerialName("truncated")
    val truncated: Boolean,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String,
    @SerialName("files")
    private val _gistFiles: JsonObject
) {
    val gistFiles: List<FileDto> = _gistFiles.values.mapNotNull {
        try {
            Json.decodeFromJsonElement(it)
        } catch (e: Exception) {
            null
        }
    }
}