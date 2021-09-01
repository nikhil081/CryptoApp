package com.example.cryptoapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.CryptoViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CryptoViewModel
    lateinit var binding: ActivityMainBinding
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
        viewModel.dataa.observe(this, { data ->
            data?.let {
                Log.i("success", "success")
                binding.data.text = data[0].quote?.USD?.price.toString()
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
}