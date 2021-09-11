package com.example.cryptoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.CoinsListingAdapter
import com.example.cryptoapp.CryptoViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentCoinListBinding
import com.example.cryptoapp.model.Coin

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoinListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoinListFragment : Fragment(), CoinsListingAdapter.CoinItemClickListener {
    private lateinit var viewModel: CryptoViewModel
    lateinit var binding: FragmentCoinListBinding
    private lateinit var adapter: CoinsListingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.fetchFromRemote()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.coinsList.observe(viewLifecycleOwner, { data ->
            data?.let {
                Log.i("success", "success")
                val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                binding.rvCoinsList.layoutManager = layoutManager
                adapter = context?.let { it1 -> CoinsListingAdapter(it1, this) }!!
                adapter.updateList(data)
                binding.rvCoinsList.adapter = adapter
            }
        })
        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let {
                Log.i("error", "error")
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                Log.i("isLoading", "isLoading")
            }
        })
    }

    override fun onCoinItemClicked(coin: Coin, position: Int, view: View) {
        Toast.makeText(context, coin.current_price.toString(), Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view)
            .navigate(R.id.navigateToDetailScreen, null)
    }

}