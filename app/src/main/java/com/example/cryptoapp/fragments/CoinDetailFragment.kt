package com.example.cryptoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cryptoapp.databinding.FragmentCoinListBinding


class CoinDetailFragment : Fragment() {


    lateinit var binding: FragmentCoinListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

}