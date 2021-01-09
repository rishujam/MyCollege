package com.example.mycollege

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.chat_text.view.*

class ChatAdapter(
        private var chats:List<TextMessage>
) :RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){
    inner class ChatViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.chat_text,parent,false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.itemView.apply {
            tvChat.text=chats[position].msg
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}