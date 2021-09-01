package com.example.cryptoapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.model.Coin
import com.example.cryptoapp.model.CoinsListingResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CryptoViewModel() : ViewModel() {
    val coinsList = MutableLiveData<List<Coin>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val service = ApiService()
    private val disposable = CompositeDisposable()
    fun fetchFromRemote() {
        loading.value = true
        disposable.add(service.getDetails().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                object : DisposableSingleObserver<CoinsListingResponse>() {
                    override fun onSuccess(list: CoinsListingResponse) {
                        coinsList.value = list
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                }
            ))
    }
}