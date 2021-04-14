package com.example.mycollege

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.contains
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.buyfrag.*
import kotlinx.android.synthetic.main.global_chat_frag.*
import kotlinx.android.synthetic.main.profilefrag.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception



class SecondActivity : AppCompatActivity(){

    val homeFragment =HomeFragment()
    val publicFragment =PublicChatFragment()
    val friendChatFragment=FriendChatFragment()
    val profileFragment =ProfileFragment()
    val buyFrag =BuyFragment()
    val create =CreateProjectFrag()
    val hireFrag =HireMentor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itemHome -> setCurrentFragment(homeFragment)
                R.id.itemPublic -> setCurrentFragment(publicFragment)
                R.id.itemFriend ->setCurrentFragment(friendChatFragment)
                R.id.itemProfile -> setCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragment)
                commit()

            }
    private fun setCurrentFragInsideFrag(fragment:Fragment)=
            supportFragmentManager.beginTransaction().apply {
                addToBackStack("stackkkkkk")
                replace(R.id.flFragment,fragment)
                commit()
            }
    fun openProfileActivity(view:View){
        Intent(this,UserProfile::class.java).also {
            startActivity(it)
        }
    }
    fun openBuyFrag(view:View){
        setCurrentFragInsideFrag(buyFrag)
    }
    fun openCreateFrag(view:View){
        setCurrentFragInsideFrag(create)
    }
    fun openMentorFrag(view:View){
        setCurrentFragInsideFrag(hireFrag)
    }

}