package com.hannibal.replacepraeparet.view.fragment

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.adapter.AccountPagerAdapter
import com.hannibal.replacepraeparet.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val tabTitleList = listOf("投稿", "写真", "投稿位置")
    val SIGN_IN_RESULT_CODE = 9999
    private lateinit var auth: FirebaseAuth

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

            auth.addAuthStateListener { mAuth ->
                if (mAuth.currentUser != null) {
                    loginButton.text = "ログアウト"
                    loginButton.setOnClickListener {
                        mAuth.signOut()
                        Log.d("LoginState", "ログアウトしました。")
                    }
                } else {
                    loginButton.text = "ログイン"
                    loginButton.setOnClickListener {
                        launchSignInFlow()
                        Log.d("LoginState", "ログインしました。")
                    }
                }
            }
        }

        return binding.root
    }

    private fun launchSignInFlow() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build(),
//            AuthUI.IdpConfig.FacebookBuilder().build(),
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(com.facebook.R.style.Theme_AppCompat_Light_NoActionBar)
                .setLogo(R.drawable.ic_launcher_round)
                .setAvailableProviders(providers)
                .build(),
            SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Log.i(
                    ContentValues.TAG,
                    "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}"
                )
            } else {
                Log.i(ContentValues.TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }
}