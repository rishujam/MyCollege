package com.example.mycollege

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.profilefrag.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder

class UserProfile : AppCompatActivity() {

    private val db =FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val db  =DbHelper(this)
        val mail=db.readData()
        tvEmail.text=mail

        setData()

        btnChanges.setOnClickListener {
            val name =etName.text.toString().trim()
            val ins =etInstitute.text.toString().trim()
            val state =etState.text.toString().trim()
            if(name.isBlank() || ins.isBlank() || state.isBlank()){
                Toast.makeText(this,"Please Fill required Details",Toast.LENGTH_LONG).show()
            }else{
                val userr =User(name,ins,state)
                saveUserDetails(userr)
                finish()
            }
        }

    }

    private fun saveUserDetails(user:User) = CoroutineScope(Dispatchers.IO).launch {
        try{
            db.collection(tvEmail.text.toString()).document("user details").set(user).await()
            withContext(Dispatchers.Main){
                Toast.makeText(this@UserProfile,"Profile Updated",Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@UserProfile,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val ref =db.collection(tvEmail.text.toString()).document("user details").get().await()
            val name =ref.getString("name")
            val college =ref.getString("institute")
            val state =ref.getString("state")
            withContext(Dispatchers.Main){
                etName.setText(name)
                etInstitute.setText(college)
                etState.setText(state)
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@UserProfile,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

}