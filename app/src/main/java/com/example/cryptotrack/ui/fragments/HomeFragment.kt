package com.example.cryptotrack.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.model.CoinModel
import com.example.cryptotrack.databinding.FragmentHomeBinding
import com.example.cryptotrack.findAppComponent
import com.example.cryptotrack.viewmodels.HomeViewModel
import com.google.android.flexbox.*
import com.example.cryptotrack.ui.adapters.CoinAdapter
import com.example.cryptotrack.ui.adapters.CoinClickListener

class HomeFragment : Fragment(
    R.layout.fragment_home
), CoinClickListener {

    interface OnCoinClickListener {
        fun onCoinClick(coinId: Int)
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<HomeViewModel> {
        requireContext().findAppComponent().viewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.coins.observe(viewLifecycleOwner) {
            setAdapter(it);
        }
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.refreshData()
        binding.progressBar.visibility = View.GONE
    }

    private fun setAdapter(coins: List<CoinModel>) {
        val adapter = CoinAdapter()
        adapter.listener = this
        bindToRecycler(adapter)
        adapter.submitList(coins)
    }

    private fun bindToRecycler(adapter: CoinAdapter) {
        val layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.SPACE_BETWEEN
            alignItems = AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP

        }
        binding.homeCoinsRecycler.layoutManager = layoutManager
        binding.homeCoinsRecycler.adapter = adapter
        binding.homeCoinsRecycler.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if( recyclerView.computeVerticalScrollOffset() > 10 ){
                        binding.fragmentTitle.setBackgroundColor(resources.getColor(R.color.toolbar_scrolled_background))
                    } else {
                        binding.fragmentTitle.setBackgroundColor(resources.getColor(R.color.background))
                    }

                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    var isLoaded = false;

                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) && recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE
                        && !isLoaded
                    ) {
                        binding.progressBar.visibility = View.VISIBLE
                        isLoaded = true
                        viewModel.loadMore();
                        binding.progressBar.visibility = View.GONE
                        /*
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                //recyclerView.scrollToPosition(adapter.itemCount - viewModel.coinsLimit)
                                //recyclerView.scrollToPosition(adapter.itemCount)
                                recyclerView.scrollToPosition(adapter.itemCount)
                            },
                            1000
                        )*/

                        /*
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                recyclerView.smoothScrollToPosition(adapter.itemCount)
                            }, 300
                        )*/
                        isLoaded = false
                    }
                }

            }
        )
    }

    override fun onClick(coinModel: CoinModel) {
        Log.d("tag", coinModel.toString())

        findNavController().navigate(
            R.id.toCoinDetailsFragment, bundleOf(
                "coinId" to coinModel.id,
                "coinChange" to coinModel.quote[coinModel.quote.keys.first()]?.percent_change_24h,
                "coinMarketCap" to coinModel.quote[coinModel.quote.keys.first()]?.market_cap,
                "coinMarketCapPercent" to coinModel.quote[coinModel.quote.keys.first()]?.market_cap_dominance,
                "coinPrice" to "%,.2f".format(
                    coinModel.quote[coinModel.quote.keys.first()]?.price
                ) + " " + coinModel.quote.keys.first()
            )
        )

    }


}

