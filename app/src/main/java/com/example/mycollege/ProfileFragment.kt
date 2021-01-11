package com.example.mycollege

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.profilefrag.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProfileFragment : Fragment(R.layout.profilefrag) {

   // private val db=FirebaseFirestore.getInstance()
    private var dbHelper:DbHelper?=null

    override fun onStart() {
        super.onStart()
        tvNameShow.text=dbHelper?.readName()
        tvEarn.setOnClickListener {
            Intent(context,EarnActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper=DbHelper(context)
    }

//    private fun setUsername(mail:String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val ref =db.collection(mail).document("user details").get().await()
//            val name =ref.getString("name")
//            withContext(Dispatchers.Main){
//                tvNameShow.text=name
//            }
//        }catch (e: Exception){
//            withContext(Dispatchers.Main){
//                Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
//            }
//        }
//    }

}

