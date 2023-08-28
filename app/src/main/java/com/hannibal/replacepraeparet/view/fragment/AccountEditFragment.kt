package com.hannibal.replacepraeparet.view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.FragmentAccountEditBinding
import com.hannibal.replacepraeparet.viewmodel.AccountFragmentViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccountEditFragment : Fragment() {
    private lateinit var binding: FragmentAccountEditBinding
    private val args: AccountEditFragmentArgs by navArgs()
    private val viewModel: AccountFragmentViewModel by activityViewModels()
    private val REQUEST_CODE = 200
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountEditBinding.inflate(inflater, container, false)

        binding.apply {
            editNameText.setText(args.name)

            backButton.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_accountEditFragment_to_nav_account)
            )

            cancelButton.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_accountEditFragment_to_nav_account)
            )

            editButton.setOnClickListener {
                val editName = editNameText.text.toString()
                if (editName == args.name && imageUri == null) {
                    Navigation.createNavigateOnClickListener(R.id.action_accountEditFragment_to_nav_account)
                } else if (editName != args.name && imageUri == null) {
                    viewModel.postEditName(editName)
                    Toast.makeText(requireContext(), "名前を変更しました。", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else if (editName == args.name && imageUri != null) {
                    viewModel.postEditImage(imageUri!!)
                    findNavController().popBackStack()
                } else if (editName != args.name && imageUri != null) {
                    viewModel.postEditName(editName)
                    viewModel.postEditImage(imageUri!!)
                    findNavController().popBackStack()
                }
            }

            addProfileImageButton.setOnClickListener {
                openGalleryForImages()
            }
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            if (data?.data != null) {
                GlobalScope.launch(Dispatchers.Main) {
                    Picasso.get().load(data.data).resize(80, 80).transform(CropCircleTransformation()).into(binding.myProfilePhoto)
                }
                imageUri = data.data
                Log.d("editProfileImage", "${data.data}")
            } else {
                Toast.makeText(requireContext(), getString(R.string.failed_load_image), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
}