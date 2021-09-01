package com.example.cryptoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.CoinItemBinding
import com.example.cryptoapp.model.Coin

class CoinsListingAdapter(
    private val listener: CoinItemClickListener
) : RecyclerView.Adapter<CoinsListingAdapter.ViewHolder>() {
    private var coinsList = listOf<Coin>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinsListingAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.coin_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinsListingAdapter.ViewHolder, position: Int) {
        holder.bindViews(coinsList[position], position)
    }

    fun updateList(list: List<Coin>) {
        coinsList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    inner class ViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(coin: Coin, position: Int) {
            binding.coinName.setOnClickListener {
                listener.onCoinItemClicked(coin, position)
            }
            binding.coin = coin
        }
    }
    interface CoinItemClickListener {
        fun onCoinItemClicked(coin: Coin, position: Int)
    }
}
