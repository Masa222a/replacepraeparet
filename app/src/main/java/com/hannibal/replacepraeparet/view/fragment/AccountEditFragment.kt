package com.hannibal.replacepraeparet.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannibal.replacepraeparet.R
import com.hannibal.replacepraeparet.databinding.FragmentAccountEditBinding
import com.hannibal.replacepraeparet.viewmodel.AccountFragmentViewModel

class AccountEditFragment : Fragment() {
    private lateinit var binding: FragmentAccountEditBinding
    private val args: AccountEditFragmentArgs by navArgs()
    private val viewModel: AccountFragmentViewModel by activityViewModels()

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
                if (editName == args.name) {
                    Navigation.createNavigateOnClickListener(R.id.action_accountEditFragment_to_nav_account)
                } else {
                    viewModel.postEditName(editName)
                    Toast.makeText(requireContext(), "名前を変更しました。", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }
}