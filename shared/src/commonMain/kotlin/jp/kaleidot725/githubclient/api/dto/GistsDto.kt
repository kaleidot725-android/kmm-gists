package jp.kaleidot725.githubclient.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class GistsDto(val list: Array<GistDto>)