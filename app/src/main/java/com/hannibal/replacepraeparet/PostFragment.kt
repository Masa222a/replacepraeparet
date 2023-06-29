package com.hannibal.replacepraeparet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannibal.replacepraeparet.databinding.FragmentPostBinding

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }
}