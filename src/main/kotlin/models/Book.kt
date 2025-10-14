package com.example.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Book(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String?,
    val year: Int?,
    var isRead: Boolean
)