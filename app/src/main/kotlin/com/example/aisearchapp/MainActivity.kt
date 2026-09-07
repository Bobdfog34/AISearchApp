package com.example.aisearchapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aisearchapp.databinding.ActivityMainBinding
import com.example.aisearchapp.ui.adapters.ChatAdapter
import com.example.aisearchapp.ui.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ChatViewModel by viewModels()
    private val chatAdapter = ChatAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        observeMessages()
    }
    
    private fun setupUI() {
        // Настройка RecyclerView
        binding.messagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }
        
        // Обработка отправки сообщения
        binding.sendButton.setOnClickListener {
            val message = binding.messageInput.text.toString()
            if (message.isNotBlank()) {
                viewModel.sendMessage(message)
                binding.messageInput.text.clear()
            }
        }
        
        // Отправка по Enter
        binding.messageInput.setOnEditorActionListener { _, _, _ ->
            val message = binding.messageInput.text.toString()
            if (message.isNotBlank()) {
                viewModel.sendMessage(message)
                binding.messageInput.text.clear()
            }
            true
        }
    }
    
    private fun observeMessages() {
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                chatAdapter.setMessages(messages)
                if (messages.isNotEmpty()) {
                    binding.messagesRecyclerView.scrollToPosition(messages.size - 1)
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.loadingIndicator.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            }
        }
    }
}