package com.hannibal.replacepraeparet.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.adapter.ChoiceImageAdapter
import com.hannibal.replacepraeparet.databinding.FragmentPostBottomSheetBinding
import com.hannibal.replacepraeparet.model.SpinnerCategory
import com.hannibal.replacepraeparet.model.SpinnerDamageTime
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPostBottomSheetBinding
    private val REQUEST_CODE = 200
    private var imageList = mutableListOf<Uri>()
    private var adapter: ChoiceImageAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBottomSheetBinding.inflate(inflater, container, false)

        val args = arguments?.getString("address_text")

        binding.apply {
            val recyclerView = recyclerView
            val layoutManager = LinearLayoutManager(activity)
            adapter = ChoiceImageAdapter(imageList)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            closeButton.setOnClickListener {
                dismiss()
            }

            addImageButton.setOnClickListener {
                openGalleryForImages()
            }

            if (args != null) {
                addressSpace.setText(args)
            }
        }

        setUpSeekBar()
        setUpSpinner()

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            if (data?.clipData != null) {
                val count = data.clipData?.itemCount

                for (i in 0 until count!!) {
                    val imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    imageList.add(imageUri)
                }
                reloadImageList(imageList)

            } else if (data?.data != null) {
                val imageUri: Uri = data.data!!
                imageList.add(imageUri)
                reloadImageList(imageList)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    private fun reloadImageList(imageList: MutableList<Uri>) {
        val listLength = imageList.count()
        if (listLength <= 5) {
            GlobalScope.launch(Dispatchers.Main) {
                adapter!!.imageList = imageList
                adapter!!.notifyDataSetChanged()
            }
        } else {
            Toast.makeText(activity, getString(R.string.choose_image_alert_text), Toast.LENGTH_SHORT).show()
            for (i in 1..listLength - 5) {
                imageList.removeLast()
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter!!.imageList = imageList
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
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