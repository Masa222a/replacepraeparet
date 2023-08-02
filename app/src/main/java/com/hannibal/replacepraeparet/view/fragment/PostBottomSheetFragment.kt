package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannibal.replacepraeparet.databinding.FragmentPostBottomSheetBinding

class PostBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPostBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBottomSheetBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}