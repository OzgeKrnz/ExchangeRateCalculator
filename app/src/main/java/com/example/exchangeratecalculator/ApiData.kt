package com.example.exchangeratecalculator


import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("items")
    val ratesItems: List<RatesItem>,

    )