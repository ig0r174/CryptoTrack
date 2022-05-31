package com.example.cryptotrack.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.CoinDetailModel
import com.example.cryptotrack.R
import com.example.cryptotrack.databinding.FragmentCoinDetailBinding
import com.example.cryptotrack.findAppComponent
import com.example.cryptotrack.viewmodels.CoinDetailsViewModel

class CoinDetailsFragment : Fragment(
    R.layout.fragment_coin_detail
) {

    private val binding by viewBinding(FragmentCoinDetailBinding::bind)

    private var coinId: Int? = null
    private var coinPrice: String? = null
    private var coinChange: Double? = null
    private var coinMarketCap: Double? = null
    private var coinMarketCapPercent: Double? = null

    private val viewModel by viewModels<CoinDetailsViewModel> {
        requireContext().findAppComponent().viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("dadatatata", arguments.toString());
        coinId = arguments?.getInt("coinId")
        coinPrice = arguments?.getString("coinPrice")
        coinChange = arguments?.getDouble("coinChange")
        coinMarketCap = arguments?.getDouble("coinMarketCap")
        coinMarketCapPercent = arguments?.getDouble("coinMarketCapPercent")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.coinInfo.observe(viewLifecycleOwner) {
            //setAdapter(it);
            Log.e("data", it.toString())
            Log.e("price", coinPrice.toString())
            setData(it)
        }

        return inflater.inflate(R.layout.fragment_coin_detail, container, false)
    }

    private fun setData(it: CoinDetailModel?) {

        if (it != null) {
            context?.let { it1 ->
                Glide.with(it1)
                    .load(it.logo)
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().circleCrop())
                    .into(binding.imageViewCoin)
            }

            binding.textDescription.text = it.description
            binding.textViewName.text = it.name
            binding.textViewPrice.text = "1 " + it.symbol + " = " +coinPrice
            binding.descriptionCard.visibility = View.VISIBLE

            binding.valueQuotes1.text = "%,.2f".format(coinChange) + "%"
            binding.valueQuotes2.text = "%,.2f".format(coinMarketCap)
            binding.valueQuotes3.text = "%,.2f".format(coinMarketCapPercent) + "%"
            binding.quoteCard.visibility = View.VISIBLE

            binding.progressBar.visibility = View.GONE
        }

    }

    override fun onResume() {
        super.onResume()
        coinId?.let {
            viewModel.refreshData(it)
        }
    }

    companion object {
        fun newInstance() = CoinDetailsFragment()
    }


}