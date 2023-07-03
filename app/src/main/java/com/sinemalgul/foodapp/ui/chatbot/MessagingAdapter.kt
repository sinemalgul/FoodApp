package com.sinemalgul.foodapp.ui.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sinemalgul.foodapp.R
import com.sinemalgul.foodapp.common.Constants.RECEIVE_ID
import com.sinemalgul.foodapp.common.Constants.SEND_ID
import com.sinemalgul.foodapp.data.model.Message
import com.sinemalgul.foodapp.databinding.ItemMessageBinding


class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder (private val binding: ItemMessageBinding) :
    RecyclerView.ViewHolder(binding.root)  {
        init{
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

            fun bind(message: Message){
                when (message.id) {
                    SEND_ID ->{
                        with(binding) {
                            tvMessage.apply {
                                text = messagesList[position].message
                                visibility = View.VISIBLE
                            }
                        }
                        binding.tvBotMessage.visibility = View.GONE
                    }
                    RECEIVE_ID -> {
                        with(binding) {
                            tvBotMessage.apply {
                                text = messagesList[position].message
                                visibility = View.VISIBLE
                            }
                        }
                        binding.tvMessage.visibility = View.GONE

                    }
                }
            }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messagesList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}
