package jp.kaleidot725.githubclient.api.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDto(
    @SerialName("filename")
    val filename: String,
    @SerialName("language")
    val language: String,
    @SerialName("raw_url")
    val rawUrl: String,
    @SerialName("size")
    val size: Int,
    @SerialName("type")
    val type: String
)