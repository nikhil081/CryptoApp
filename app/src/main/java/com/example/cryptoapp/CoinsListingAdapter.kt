package com.example.cryptoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.CoinItemBinding

class CoinsListingAdapter() : RecyclerView.Adapter<CoinsListingAdapter.ViewHolder>() {
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
        holder.bindViews()
    }

    override fun getItemCount(): Int {
        return 200
    }

    inner class ViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.clClassSlots.setOnClickListener {
//                onClassSlotSelectedCallBack(adapterPosition)
//            }
//        }

        fun bindViews() {
            binding.coinName.text = "list item"
        }

    }
}
