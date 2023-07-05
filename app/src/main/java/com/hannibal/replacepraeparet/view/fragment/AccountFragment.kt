package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.hannibal.replacepraeparet.adapter.AccountPagerAdapter
import com.hannibal.replacepraeparet.databinding.FragmentAccountBinding

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
        }

        return binding.root
    }
}