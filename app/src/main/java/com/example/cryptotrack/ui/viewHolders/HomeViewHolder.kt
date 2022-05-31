package com.example.cryptotrack.ui.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CoinModel
import com.example.cryptotrack.R
import com.example.cryptotrack.databinding.ItemArticleBinding
import com.example.cryptotrack.ui.adapters.CoinClickListener

class HomeViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val context get() = binding.root.context

    fun bind(coinItem: CoinModel, listener: CoinClickListener?) {
        with(binding) {
            Glide.with(context)
                .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + coinItem.id + ".png")
                .apply(RequestOptions().centerCrop())
                .apply(RequestOptions().circleCrop())
                .into(itemCoinImage)

            itemArticleHeader.text = coinItem.name
            itemArticleCategory.text = coinItem.symbol
            itemArticlePrice.text =
                "%,.2f".format(coinItem.quote[coinItem.quote.keys.first()]?.price) + " " + coinItem.quote.keys.first();

            if (coinItem.quote[coinItem.quote.keys.first()]!!.percent_change_24h < 0) {
                itemArticlePercent.setTextAppearance(R.style.Coin_PercentMinus);
                itemArticlePercent.text =
                    "%,.2f".format(coinItem.quote[coinItem.quote.keys.first()]?.percent_change_24h) + "%";
            } else {
                itemArticlePercent.setTextAppearance(R.style.Coin_PercentPlus);
                itemArticlePercent.text =
                    "+%,.2f".format(coinItem.quote[coinItem.quote.keys.first()]?.percent_change_24h) + "%";
            }

            tariffsCard.setOnClickListener {
                Log.e("click", listener.toString())
                listener?.onClick(coinItem);
            }

            /*
            tariffsCard.setOnClickListener(new View.onClickListener(){

            })*/

        }
    }
}