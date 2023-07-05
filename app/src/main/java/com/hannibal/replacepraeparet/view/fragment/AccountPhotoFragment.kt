package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hannibal.replacepraeparet.databinding.FragmentAccountPhotoBinding

class AccountPhotoFragment : Fragment() {
    private lateinit var binding: FragmentAccountPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }
}