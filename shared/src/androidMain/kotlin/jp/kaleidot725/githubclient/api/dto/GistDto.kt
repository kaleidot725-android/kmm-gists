package jp.kaleidot725.githubclient.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    @SerialName("files")
    val files: List<FileDto>,
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
    val isPublic: Boolean,
    @SerialName("truncated")
    val truncated: Boolean,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)