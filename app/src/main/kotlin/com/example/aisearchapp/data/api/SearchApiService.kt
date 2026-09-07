package com.example.aisearchapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.aisearchapp.data.models.SearchResponse

interface SearchApiService {
    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("cx") searchEngineId: String
    ): SearchResponse
}