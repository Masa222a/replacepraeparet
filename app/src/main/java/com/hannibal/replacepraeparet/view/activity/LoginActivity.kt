package com.hannibal.replacepraeparet.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val AUTH_REQUEST_CODE = 7192
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener
    private lateinit var providers: List<AuthUI.IdpConfig>

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(listener)
    }

    override fun onStop() {
        super.onStop()
        if (listener != null) {
            firebaseAuth.removeAuthStateListener(listener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
//            AuthUI.IdpConfig.TwitterBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
        )

        firebaseAuth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener(object : FirebaseAuth.AuthStateListener, (FirebaseAuth) -> Unit {
            override fun invoke(firebaseAuth: FirebaseAuth) {
            }
            override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                //get user
                val user = firebaseAuth.currentUser
                if (user != null) {
                    Toast.makeText(this@LoginActivity, "You already login with uid: "+user.uid, Toast.LENGTH_SHORT).show()
                } else {
                    //Login
                    startActivity(
                        AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build()
                    )
                }
            }
        })
    }
}