package com.sinemalgul.foodapp.ui.chatbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinemalgul.foodapp.common.Constants.RECEIVE_ID
import com.sinemalgul.foodapp.common.Constants.SEND_ID
import com.sinemalgul.foodapp.data.model.Message
import com.sinemalgul.foodapp.databinding.FragmentMessageBinding
import kotlinx.coroutines.*
import java.sql.Time


class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recyclerView()

        clickEvents()

        customMessage("Merhaba! Foodies ChatBot'una hoş geldin!")
    }

    private fun clickEvents() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        binding.rvMessages.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        binding.rvMessages.layoutManager = layoutManager
    }


    private fun sendMessage() {
        val message = binding.etMessage.text.toString()

        if(message.isNotEmpty()) {
            messagesList.add(0,Message(message, SEND_ID))
            binding.etMessage.setText("")
            adapter.insertMessage(Message(message, SEND_ID))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                val response= BotResponse.basicResponses(message)

                messagesList.add(0, Message(response, RECEIVE_ID))
                adapter.insertMessage(Message(response, RECEIVE_ID))
                binding.rvMessages.scrollToPosition(0)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(0)
            }

        }
    }


    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val customMessage = Message(message, RECEIVE_ID)
                messagesList.add(0, customMessage)
                adapter.insertMessage(customMessage)
                binding.rvMessages.scrollToPosition(0)
            }
        }
    }
}