package com.example.aisearchapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisearchapp.data.models.AIChatMessage
import com.example.aisearchapp.data.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel : ViewModel() {
    private val searchRepository = SearchRepository()
    
    private val _messages = MutableStateFlow<List<AIChatMessage>>(emptyList())
    val messages: StateFlow<List<AIChatMessage>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank()) return
        
        // Добавляем сообщение пользователя
        val userMsg = AIChatMessage(
            id = UUID.randomUUID().toString(),
            text = userMessage,
            isFromUser = true
        )
        _messages.value = _messages.value + userMsg
        
        // Получаем ответ от AI
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Ищем информацию в интернете
                val searchResults = searchRepository.search(userMessage)
                
                // Генерируем ответ на основе результатов
                val aiResponse = searchRepository.generateResponse(userMessage, searchResults)
                
                val aiMsg = AIChatMessage(
                    id = UUID.randomUUID().toString(),
                    text = aiResponse,
                    isFromUser = false
                )
                _messages.value = _messages.value + aiMsg
            } catch (e: Exception) {
                val errorMsg = AIChatMessage(
                    id = UUID.randomUUID().toString(),
                    text = "Ошибка: ${e.message}",
                    isFromUser = false
                )
                _messages.value = _messages.value + errorMsg
            } finally {
                _isLoading.value = false
            }
        }
    }
}