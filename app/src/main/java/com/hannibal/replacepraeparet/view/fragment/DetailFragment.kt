package com.hannibal.replacepraeparet.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.model.DetailManager
import com.hannibal.replacepraeparet.adapter.DetailPagerAdapter
import com.hannibal.replacepraeparet.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val tabTitleList = listOf("ビザ・渡航", "大使館")
    private val detailManager = DetailManager()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.apply {
            val adapter = DetailPagerAdapter(args.flag, childFragmentManager, lifecycle)

            detailViewPager.adapter = adapter
            TabLayoutMediator(tabLayout, detailViewPager) { tab, position ->
                tab.text = tabTitleList[position]
            }.attach()

            backButton.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_nav_information)
            )

            collapsingToolBar.let {
                it.title = args.flag.name
                it.setCollapsedTitleTextColor(Color.WHITE)
                it.setExpandedTitleColor(Color.WHITE)
                detailManager.setPhoto(eachCountryPhoto, args.flag.engName)
            }
        }

        return binding.root
    }
}
