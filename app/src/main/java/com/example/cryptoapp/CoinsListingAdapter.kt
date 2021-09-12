package com.example.cryptoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoapp.databinding.CoinItemBinding
import com.example.cryptoapp.model.Coin

class CoinsListingAdapter(
    private val context: Context,
    private val listener: CoinItemClickListener
) : RecyclerView.Adapter<CoinsListingAdapter.ViewHolder>(), Filterable {
    var coinsList: ArrayList<Coin> = ArrayList()
    var coinsListFiltered: ArrayList<Coin> = ArrayList()
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
        holder.bindViews(coinsListFiltered[position], position)
    }

    fun addData(list: List<Coin>) {
        coinsList = list as ArrayList<Coin>
        coinsListFiltered = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return coinsListFiltered.size
    }

    inner class ViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(coin: Coin, position: Int) {
            binding.coinName.setOnClickListener {
                listener.onCoinItemClicked(coin, position, it)
            }
            binding.coin = coin
            Glide.with(context)
                .load(coin.image)
                .into(binding.coinImage)
            val str = String.format("%.2f", coin.price_change_percentage_24h)
            if (coin.price_change_percentage_24h!! < 0) {
                binding.coinPercentageChange.setTextColor(context.resources.getColor(R.color.red_fab))
                binding.coinPrice.setTextColor(context.resources.getColor(R.color.red_fab))

            } else {
                binding.coinPercentageChange.setTextColor(context.resources.getColor(R.color.colorGreen))
                binding.coinPrice.setTextColor(context.resources.getColor(R.color.colorGreen))
            }
            binding.coinPercentageChange.text = str
        }
    }

    interface CoinItemClickListener {
        fun onCoinItemClicked(coin: Coin, position: Int, view: View)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence?.toString() ?: ""
                if (charString.isEmpty()) coinsListFiltered = coinsList else {
                    val filteredList = ArrayList<Coin>()
                    coinsList
                        .filter {
                            (it.name!!.contains(charSequence!!)) or (it.symbol!!.contains(
                                charSequence
                            ))
                        }
                        .forEach { filteredList.add(it) }
                    coinsListFiltered = filteredList

                }
                return FilterResults().apply { values = coinsListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                coinsListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Coin>
                notifyDataSetChanged()
            }


        }
    }
}
