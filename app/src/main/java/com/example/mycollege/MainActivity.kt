package com.example.mycollege

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.profilefrag.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

const val REQUEST_CODE_SIGN_IN =0

class MainActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var email:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val options =GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.webclient_id))
                    .requestEmail()
                    .build()
            val signInClient = GoogleSignIn.getClient(this,options)
            signInClient.signInIntent.also {
                startActivityForResult(it, REQUEST_CODE_SIGN_IN)
            }
        }
    }

    @Override
    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }

    @Override
    override fun onStop() {
        super.onStop()
        finish()
    }

    private fun googleAuthForFirebase(account:GoogleSignInAccount){
        val credentials =GoogleAuthProvider.getCredential(account.idToken,null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"Successfully logged In",Toast.LENGTH_SHORT).show()
                    checkLoggedInState()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE_SIGN_IN){
            val account =GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthForFirebase(it)
                email=account.email.toString()
            }
            val db =DbHelper(this@MainActivity)
            db.insertData(email)
        }
    }

    private fun checkLoggedInState(){
        if(auth.currentUser!=null){
            Intent(this,SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}









