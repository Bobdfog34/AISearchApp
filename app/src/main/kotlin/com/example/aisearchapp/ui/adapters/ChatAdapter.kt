package com.example.aisearchapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aisearchapp.data.models.AIChatMessage
import com.example.aisearchapp.databinding.ItemChatMessageBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {
    private var messages = listOf<AIChatMessage>()
    
    fun setMessages(newMessages: List<AIChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemChatMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MessageViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }
    
    override fun getItemCount(): Int = messages.size
    
    class MessageViewHolder(private val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(message: AIChatMessage) {
            binding.messageText.text = message.text
            
            if (message.isFromUser) {
                binding.messageText.setBackgroundColor(0xFF2196F3.toInt())
                binding.messageText.setTextColor(0xFFFFFFFF.toInt())
            } else {
                binding.messageText.setBackgroundColor(0xFFE0E0E0.toInt())
                binding.messageText.setTextColor(0xFF000000.toInt())
            }
        }
    }
}