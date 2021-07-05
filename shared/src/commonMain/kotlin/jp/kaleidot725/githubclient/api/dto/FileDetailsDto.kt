package jp.kaleidot725.githubclient.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDetailsDto(
    @SerialName("filename")
    val filename: String,
    @SerialName("type")
    val type: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("raw_url")
    val rawUrl: String,
    @SerialName("size")
    val size: Int,
    @SerialName("truncated")
    val truncated: Boolean,
    @SerialName("content")
    val content: String
)