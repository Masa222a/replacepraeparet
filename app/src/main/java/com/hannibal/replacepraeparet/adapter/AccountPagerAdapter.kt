package com.hannibal.replacepraeparet.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hannibal.replacepraeparet.view.fragment.AccountPhotoFragment
import com.hannibal.replacepraeparet.view.fragment.AccountPostFragment
import com.hannibal.replacepraeparet.view.fragment.AccountPostLocationFragment

class AccountPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    enum class Page(val position: Int) {
        Post(0),
        Photo(1),
        PostLocation(2);

        companion object {
            fun positionOf(position: Int) = values().firstOrNull { it.position == position } ?: throw IllegalArgumentException("invalid position")
        }
    }

    override fun getItemCount(): Int {
        return Page.values().size
    }

    override fun createFragment(position: Int): Fragment {
        when(Page.positionOf(position)) {
            Page.Post -> {
                val fragment = AccountPostFragment()
                return fragment
            }
            Page.Photo -> {
                val fragment = AccountPhotoFragment()
                return fragment
            }
            Page.PostLocation -> {
                val fragment = AccountPostLocationFragment()
                return fragment
            }
            else -> throw IllegalArgumentException("invalid position")
        }
    }

}