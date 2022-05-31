package com.example.cryptotrack.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.CoinModel
import com.example.cryptotrack.databinding.ItemArticleBinding
import com.example.cryptotrack.ui.viewHolders.HomeViewHolder
import com.example.cryptotrack.utils.DefaultItemCallback

class CoinAdapter : ListAdapter<
        CoinModel,
        HomeViewHolder>(DefaultItemCallback<CoinModel>()) {

    var listener: CoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}

interface CoinClickListener {
    fun onClick(coinModel: CoinModel)
}