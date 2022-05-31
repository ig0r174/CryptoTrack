package com.example.cryptotrack.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cryptotrack.R
import com.example.cryptotrack.findAppComponent
import com.example.cryptotrack.viewmodels.HomeViewModel
import com.example.cryptotrack.viewmodels.ProfileViewModel
import com.example.cryptotrack.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(
    R.layout.fragment_profile
) {
    private val currencies = arrayOf(
        "RUB (рубли)",
        "USD (доллары США)",
        "EUR (евро)",
        "GBP (фунт стерлингов)",
        "KZT (тенге)"
    );

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel by viewModels<ProfileViewModel> {
        requireContext().findAppComponent().viewModelFactory()
    }

    private val homeViewModel by viewModels<HomeViewModel> {
        requireContext().findAppComponent().viewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, currencies) }
            .also { adapter ->
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item = parent.getItemAtPosition(position) as String
                    val currency = item.split(" ")[0]
                    viewModel.saveCurrency(currency)
                    homeViewModel.clean()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        binding.spinner.onItemSelectedListener = itemSelectedListener

    }
}