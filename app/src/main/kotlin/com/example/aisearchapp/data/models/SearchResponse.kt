package com.example.aisearchapp.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val items: List<SearchItem>? = null,
    @SerializedName("error")
    val error: String? = null
)

data class SearchItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("snippet")
    val snippet: String
)

data class AIChatMessage(
    val id: String = "",
    val text: String,
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)