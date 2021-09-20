package com.example.cryptoapp



object Validation {


    fun check(currency:String):Boolean{

        return currency.isNotEmpty()

    }
}