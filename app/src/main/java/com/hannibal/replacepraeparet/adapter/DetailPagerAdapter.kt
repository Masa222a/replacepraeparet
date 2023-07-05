package com.hannibal.replacepraeparet.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hannibal.replacepraeparet.model.Flag
import com.hannibal.replacepraeparet.view.fragment.DetailEmbassyFragment
import com.hannibal.replacepraeparet.view.fragment.DetailVisaFragment

class DetailPagerAdapter(val flag: Flag, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    enum class Page(val position: Int) {
        Visa(0),
        Embassy(1);

        companion object {
            fun positionOf(position: Int) = values().firstOrNull { it.position == position } ?: throw IllegalArgumentException("invalid position")
        }
    }

    override fun getItemCount(): Int {
        return Page.values().size
    }

    override fun createFragment(position: Int): Fragment {
        when(Page.positionOf(position)) {
            Page.Visa -> {
                val fragment = DetailVisaFragment()
                val bundle = Bundle()
                bundle.putSerializable("flag", flag)
                fragment.arguments = bundle
                return fragment
            }
            Page.Embassy -> {
                val fragment = DetailEmbassyFragment()
                val bundle = Bundle()
                bundle.putSerializable("flag", flag)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                throw IllegalArgumentException("invalid position")
            }
        }
    }

}
