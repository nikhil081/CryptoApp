package com.example.cryptoapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class cryptoViewModel() : ViewModel() {
    val dataa = MutableLiveData<model>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val service = ApiService()
    private val disposable = CompositeDisposable()
    fun fetchFromRemote() {
        loading.value = true
        disposable.add(service.getDetails("assets", "DOGE").subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                object : DisposableSingleObserver<model>() {
                    override fun onSuccess(data: model) {
                        dataa.value = data
                        error.value = false
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