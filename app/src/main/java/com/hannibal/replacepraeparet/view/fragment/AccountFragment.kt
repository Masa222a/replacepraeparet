package com.hannibal.replacepraeparet.view.fragment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.adapter.AccountPagerAdapter
import com.hannibal.replacepraeparet.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val tabTitleList = listOf("投稿", "写真", "投稿位置")
    private lateinit var auth: FirebaseAuth
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            val adapter = AccountPagerAdapter(childFragmentManager, lifecycle)

            viewpagerInAccount.adapter = adapter
            TabLayoutMediator(tabLayout, viewpagerInAccount) { tab, position ->
                tab.text = tabTitleList[position]
            }.attach()

            loginButton.setOnClickListener {
                auth.addAuthStateListener {
                    if (it.currentUser != null) {
                        loginButton.text = getString(R.string.logout)
                        loginButton.setOnClickListener {
                            signOut()
                        }
                    } else {
                        loginButton.text = getString(R.string.login)
                        loginButton.setOnClickListener {
                            startSignIn()
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun startSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setTheme(com.facebook.R.style.Theme_AppCompat_Light_NoActionBar)
            .setLogo(R.drawable.ic_launcher_round)
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
        Log.d("LoginState", "ログインしました。")
        Log.d("LoginInfoSignIn", "${Firebase.auth.currentUser}")
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        Log.d("LoginSignOut", "${FirebaseAuth.getInstance().currentUser}")
        Log.d("LoginState", "ログアウトしました。")
        Log.d("LoginInfoSignOut", "${Firebase.auth.currentUser}")
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            Log.d("LoginSignInSuccess", "${user?.providerData}")
            Log.d("LoginSignInSuccess", "サインインに成功しました。")

        } else {
            Log.d("LoginSignInFailed", "サインインに失敗しました。")
        }
    }
}