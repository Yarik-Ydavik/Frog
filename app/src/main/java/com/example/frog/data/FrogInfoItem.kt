package com.example.frog.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FrogInfoItem(
    val description: String,
    @SerialName("img_src")
    val img_src: String,
    val name: String,
    val type: String
)