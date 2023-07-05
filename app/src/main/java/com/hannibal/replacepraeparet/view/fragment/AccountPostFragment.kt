package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hannibal.replacepraeparet.databinding.FragmentAccountPostBinding

class AccountPostFragment : Fragment() {
    private lateinit var binding: FragmentAccountPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountPostBinding.inflate(inflater, container, false)
        return binding.root
    }
}