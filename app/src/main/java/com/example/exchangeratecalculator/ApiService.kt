package com.example.exchangeratecalculator

import retrofit2.http.GET

interface ApiService {
    @GET("series=TP.DK.USD.A-TP.DK.AUD.A-" +
            "TP.DK.DKK.A-TP.DK.EUR.A-TP.DK.GBP.A-TP.DK.CHF.A-" +
            "TP.DK.SEK.A-TP.DK.CAD.A-TP.DK.KWD.A-TP.DK.NOK.A-TP.DK.SAR.A-" +
            "TP.DK.JPY.A-TP.DK.BGN.A-TP.DK.RON.A-TP.DK.RUB.A-TP.DK.IRR.A-TP.DK.CNY.A-" +
            "TP.DK.PKR.A-TP.DK.QAR.A-TP.DK.KRW.A-TP.DK.AZN.A-TP.DK.AED.A-" +
            "&startDate=08-02-2024&endDate=08-02-2024&type=json&key=EWwcOC3ibO")

    suspend fun getexchangeRates(
    ):ApiData
}