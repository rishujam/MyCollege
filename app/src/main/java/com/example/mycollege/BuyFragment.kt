package com.example.mycollege

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.buyfrag.*

class BuyFragment :Fragment(R.layout.buyfrag), ItemAdapter.OnItemClickListener{

    override fun onStart() {
        super.onStart()
        val item1 =Item(R.drawable.chatlogo,"Chat App", "This app is a simple chatting app base on firebase cloud messaging and share images. It is a group chat app no private chat feature is available in this")
        val item2 =Item(R.drawable.musicapplogo,"Music Player", "This app is a simple chatting app base on firebase cloud messaging and share images. It is a group chat app no private chat feature is available in this")
        val item3 =Item(R.drawable.todoicon,"To Do App", "This app is a simple chatting app base on firebase cloud messaging and share images. It is a group chat app no private chat feature is available in this")
        val item4 =Item(R.drawable.libraryapplogo,"Library Management App", "This app is a simple chatting app base on firebase cloud messaging and share images. It is a group chat app no private chat feature is available in this")
        val item5 =Item(R.drawable.restaurantapplogo,"Restaurant Management", "This app is a simple chatting app base on firebase cloud messaging and share images. It is a group chat app no private chat feature is available in this")
        val item= arrayListOf<Item>()
        item.add(item1)
        item.add(item2)
        item.add(item3)
        item.add(item4)
        item.add(item5)
        val itemAdapter=ItemAdapter(item,this)
        rvProducts.adapter=itemAdapter
        rvProducts.layoutManager=LinearLayoutManager(context)
    }

    override fun onItemClick(position: Int) {
        Intent(context,ProductActivity::class.java).also {
            startActivity(it)
        }
    }

}