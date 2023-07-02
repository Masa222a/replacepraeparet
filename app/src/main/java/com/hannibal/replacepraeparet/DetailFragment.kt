package com.hannibal.replacepraeparet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import com.hannibal.replacepraeparet.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val tabTitleList = listOf("ビザ・渡航", "大使館")
    private val detailManager = DetailManager()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val flag = arguments?.getSerializable("country") as Flag

        binding.apply {
            val adapter = DetailPagerAdapter(flag, parentFragmentManager, lifecycle)

            detailViewPager.adapter = adapter
            TabLayoutMediator(tabLayout, detailViewPager) { tab, position ->
                tab.text = tabTitleList[position]
            }.attach()

            backButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            collapsingToolBar.let {
                it.title = flag.name
                it.setCollapsedTitleTextColor(Color.WHITE)
                it.setExpandedTitleColor(Color.WHITE)
                detailManager.setPhoto(eachCountryPhoto, flag.engName)
            }
        }

        return binding.root
    }
}
