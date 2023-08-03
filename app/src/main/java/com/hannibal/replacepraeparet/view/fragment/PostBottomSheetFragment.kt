package com.hannibal.replacepraeparet.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannibal.replacepraeparet.R
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

        setUpSeekBar()

        return binding.root
    }

    private fun setUpSeekBar() {
        binding.apply {
            alertRangeSeekBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    @SuppressLint("SetTextI18n")
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean,
                    ) {
                        if (progress != 0) {
                            val progressValue = progress.toFloat() / 20
                            if (progressValue < 1.0) {
                                seekBarText.text = "警戒範囲　半径：　${progress.toFloat() / 20 * 1000}m"
                            } else {
                                seekBarText.text = "警戒範囲　半径：　${progress.toFloat() / 20}km"
                            }
                        } else {
                            seekBarText.text = getString(R.string.specific_position)
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                }
            )
        }
    }
}