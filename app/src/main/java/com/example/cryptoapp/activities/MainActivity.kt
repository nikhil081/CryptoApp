package com.example.cryptoapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.CoinsListingAdapter
import com.example.cryptoapp.CryptoViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.model.Coin

class MainActivity : AppCompatActivity(), CoinsListingAdapter.CoinItemClickListener {
    private lateinit var viewModel: CryptoViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CoinsListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.fetchFromRemote()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.coinsList.observe(this, { data ->
            data?.let {
                Log.i("success", "success")
                val layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
                binding.rvCoinsList.layoutManager = layoutManager
                adapter = CoinsListingAdapter(this)
                adapter.updateList(data)
                binding.rvCoinsList.adapter = adapter
            }
        })
        viewModel.error.observe(this, { error ->
            error?.let {
                Log.i("error", "error")
            }
        })
        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let {
                Log.i("isLoading", "isLoading")
            }
        })
    }

    override fun onCoinItemClicked(coin: Coin, position: Int) {
        Toast.makeText(this, coin.current_price.toString(), Toast.LENGTH_SHORT).show()
    }
}