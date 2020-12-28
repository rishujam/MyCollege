package com.example.mycollege

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.profilefrag.*

class SecondActivity : AppCompatActivity() {


    val homeFragment =HomeFragment()
    val chatFragment =ChatFragment()
    val profileFragment =ProfileFragment()
    val buyFrag =BuyFragment()
    val create =CreateProjectFrag()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

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
    private fun setCurrentFragBackStack(fragment:Fragment)=
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
        setCurrentFragBackStack(buyFrag)
    }

    fun openCreateFrag(view:View){
        setCurrentFragBackStack(create)
    }

}