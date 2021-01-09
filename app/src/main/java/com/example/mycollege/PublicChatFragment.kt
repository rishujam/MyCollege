package com.example.mycollege

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.global_chat_frag.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class PublicChatFragment:Fragment(R.layout.global_chat_frag) {

    private val chatCollectionRef = Firebase.firestore.collection("all chats")
    private val chats = arrayListOf<TextMessage>()
    private lateinit var message:String
    lateinit var chatAdapter:ChatAdapter

    override fun onStart() {
        super.onStart()
        realtimeChats()
        chatAdapter =ChatAdapter(chats)
        rvChatsView.adapter =chatAdapter
        rvChatsView.layoutManager=LinearLayoutManager(context)


        button.setOnClickListener {
            hideSoftKeyboard(context as Activity)
            message=etMessage.text.toString().trim()
            if(message.isNotBlank()){
                saveChats(TextMessage(message, System.currentTimeMillis()))
            }else{
                Toast.makeText(context, "Please write something", Toast.LENGTH_LONG).show()
            }
            etMessage.setText("")
        }
    }

    private fun saveChats(message: TextMessage) = CoroutineScope(Dispatchers.IO).launch {
        try {
            chatCollectionRef.add(message).await()
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun realtimeChats() = CoroutineScope(Dispatchers.IO).launch {
        chatCollectionRef.orderBy("time").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()             //warning*********  toast on io thread
                return@addSnapshotListener
            }
            querySnapshot?.let {
                for(document in it){
                    val msg =document.toObject<TextMessage>()
                    if(!chats.contains(msg)){
                        chats.add(msg)
                        chatAdapter.notifyDataSetChanged()
                        rvChatsView.scrollToPosition(chats.size-1)
                    }
                }
            }
        }
    }
    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

}