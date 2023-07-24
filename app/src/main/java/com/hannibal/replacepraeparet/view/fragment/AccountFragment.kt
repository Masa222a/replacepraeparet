package com.hannibal.replacepraeparet.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.tabs.TabLayoutMediator
import com.hannibal.replacepraeparet.adapter.AccountPagerAdapter
import com.hannibal.replacepraeparet.databinding.FragmentAccountBinding
import com.hannibal.replacepraeparet.view.activity.LoginActivity

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val tabTitleList = listOf("投稿", "写真", "投稿位置")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.apply {
            val adapter = AccountPagerAdapter(childFragmentManager, lifecycle)

            viewpagerInAccount.adapter = adapter
            TabLayoutMediator(tabLayout, viewpagerInAccount) { tab, position ->
                tab.text = tabTitleList[position]
            }.attach()

            myProfilePhoto.shapeAppearanceModel =
                ShapeAppearanceModel
                    .builder()
                    .setAllCornerSizes(ShapeAppearanceModel.PILL)
                    .build()

            loginButton.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}