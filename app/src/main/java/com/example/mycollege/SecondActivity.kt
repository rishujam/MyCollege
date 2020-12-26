package com.example.mycollege

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.profilefrag.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val homeFragment =HomeFragment()
        val chatFragment =ChatFragment()
        val profileFragment =ProfileFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itemHome -> setCurrentFragment(homeFragment)
                R.id.itemMessage -> setCurrentFragment(chatFragment)
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

    fun openProfileActivity(view:View){
        Intent(this,UserProfile::class.java).also {
            startActivity(it)
        }
    }

}