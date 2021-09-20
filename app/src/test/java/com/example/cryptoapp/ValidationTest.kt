package com.example.cryptoapp

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth.assertThat


@RunWith(JUnit4::class)
class ValidationTest{


    @Test
    fun isInPutValid(){
        val currency = "usd"
        val result = Validation.check(currency)
        assertThat(result).isTrue()

    }

    @Test
    fun isInPutInValid(){
        val currency = ""
        val result = Validation.check(currency)
        assertThat(result).isFalse()
    }
}