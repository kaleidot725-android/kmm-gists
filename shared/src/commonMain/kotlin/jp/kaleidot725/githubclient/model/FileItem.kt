package jp.kaleidot725.githubclient.model

data class FileItem(
    val name: String,
    val language: String?,
    val type: String?,
    val content: String
)
