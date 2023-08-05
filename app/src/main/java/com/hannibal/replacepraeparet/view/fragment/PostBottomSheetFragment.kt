package com.hannibal.replacepraeparet.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.FragmentPostBottomSheetBinding
import com.hannibal.replacepraeparet.model.SpinnerCategory
import com.hannibal.replacepraeparet.model.SpinnerDamageTime

class PostBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPostBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBottomSheetBinding.inflate(inflater, container, false)

        binding.apply {
            closeButton.setOnClickListener {
                dismiss()
            }

            addImageButton.setOnClickListener {


            }
        }

        setUpSeekBar()
        setUpSpinner()

        return binding.root
    }

    private fun setUpSpinner() {
        val categoryAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        val damageAdapter = ArrayAdapter<String>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        categoryAdapter.setDropDownViewResource(com.facebook.share.R.layout.support_simple_spinner_dropdown_item)
        damageAdapter.setDropDownViewResource(com.facebook.share.R.layout.support_simple_spinner_dropdown_item)

        SpinnerCategory.values().forEach {
            categoryAdapter.add(it.category)
        }

        SpinnerDamageTime.values().forEach {
            damageAdapter.add(it.times)
        }

        binding.apply {
            categoryList.adapter = categoryAdapter
            timeOfDamageList.adapter = damageAdapter

            categoryList.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }

            timeOfDamageList.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }
        }
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