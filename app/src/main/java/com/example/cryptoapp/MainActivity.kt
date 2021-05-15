package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: cryptoViewModel
    lateinit var dataa: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        dataa = findViewById<TextView>(R.id.data)
        viewModel = ViewModelProvider(this).get(cryptoViewModel::class.java)
        viewModel.fetchFromRemote()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dataa.observe(this, { data ->
            data?.let {
                dataa.text = data.data!![0].price.toString()
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