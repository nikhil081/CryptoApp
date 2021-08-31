package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CryptoViewModel
    lateinit var dataa: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        dataa = findViewById<TextView>(R.id.data)
        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.fetchFromRemote()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dataa.observe(this, { data ->
            data?.let {
                Log.i("success", "success")
                Log.i("success", data.get(0).quote?.USD?.price.toString())
                dataa.text = data.get(0).quote?.USD?.price.toString()
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