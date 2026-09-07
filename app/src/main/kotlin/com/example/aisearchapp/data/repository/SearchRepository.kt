package com.example.aisearchapp.data.repository

import com.example.aisearchapp.data.api.SearchApiService
import com.example.aisearchapp.data.models.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRepository {
    private val apiService: SearchApiService
    
    // Используем Google Custom Search API
    // Получи свои ключи здесь: https://programmablesearchengine.google.com/
    private val API_KEY = "AIzaSyDemoKeyChangeThis" // Замени на реальный ключ
    private val SEARCH_ENGINE_ID = "demoSearchEngineId" // Замени на реальный ID
    
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/customsearch/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        apiService = retrofit.create(SearchApiService::class.java)
    }
    
    suspend fun search(query: String): List<SearchItem> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.search(query, API_KEY, SEARCH_ENGINE_ID)
            response.items?.take(5) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun generateResponse(userQuery: String, searchResults: List<SearchItem>): String {
        return withContext(Dispatchers.Default) {
            if (searchResults.isEmpty()) {
                "К сожалению, я не смог найти информацию по вашему запросу: \"$userQuery\""
            } else {
                val resultSummary = searchResults.joinToString("\n\n") { item ->
                    "📌 ${item.title}\n${item.snippet}\n🔗 ${item.link}"
                }
                "Вот что я нашел по вашему запросу \"$userQuery\":\n\n$resultSummary"
            }
        }
    }
}