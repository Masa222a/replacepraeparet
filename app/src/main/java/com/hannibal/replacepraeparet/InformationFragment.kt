package com.hannibal.replacepraeparet

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hannibal.replacepraeparet.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformationBinding
    private var adapter: CountriesListAdapter? = null
    private var flagList = listOf<Flag>()
    private val viewModel: InformationFragmentViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)

        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        setupObserve()

        adapter = CountriesListAdapter(flagList)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(view?.context)
            recyclerView.adapter = adapter

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        viewModel.getFlagList(XmlManager.Region.indexOf(tab.position))
                        adapter?.flagList = viewModel.flagList.value!!
                        viewModel.tabPosition.postValue(tab.position)

                        recyclerView.scrollToPosition(0)
                        adapter?.notifyDataSetChanged()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    recyclerView.scrollToPosition(0)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    recyclerView.scrollToPosition(0)
                }

            })
        }

        adapter!!.setOnCountryClickListener(
            object : CountriesListAdapter.OnCountryCellClickListener {
                override fun onItemClick(flag: Flag) {
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("country", flag)
                    fragment.arguments = bundle
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.navHost, fragment)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit()
                }
            }
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.tabPosition.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(it)?.select()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserve() {
        viewModel.getFlagList(XmlManager.Region.Asia)

        viewModel.flagList.observe(viewLifecycleOwner) {
            adapter?.flagList = it
            adapter?.notifyDataSetChanged()
        }
    }
}