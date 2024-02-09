package com.example.exchangeratecalculator


import com.google.gson.annotations.SerializedName

data class RatesItem(
    @SerializedName("TP_DK_AED_A")
    val AED: String,
    @SerializedName("TP_DK_AUD_A")
    val AUD: String,
    @SerializedName("TP_DK_AZN_A")
    val AZN: String,
    @SerializedName("TP_DK_BGN_A")
    val BGN: String,
    @SerializedName("TP_DK_CAD_A")
    val CAD: String,
    @SerializedName("TP_DK_CHF_A")
    val CHF: String,
    @SerializedName("TP_DK_CNY_A")
    val CNA: String,
    @SerializedName("TP_DK_DKK_A")
    val DKK: String,
    @SerializedName("TP_DK_EUR_A")
    val EUR: String,
    @SerializedName("TP_DK_GBP_A")
    val GBP: String,
    @SerializedName("TP_DK_IRR_A")
    val IRR: String,
    @SerializedName("TP_DK_JPY_A")
    val JPY: String,
    @SerializedName("TP_DK_KRW_A")
    val KRW: String,
    @SerializedName("TP_DK_KWD_A")
    val KWD: String,
    @SerializedName("TP_DK_NOK_A")
    val NOK: String,
    @SerializedName("TP_DK_PKR_A")
    val PKR: String,
    @SerializedName("TP_DK_QAR_A")
    val QAR: String,
    @SerializedName("TP_DK_RON_A")
    val RON: String,
    @SerializedName("TP_DK_RUB_A")
    val RUB: String,
    @SerializedName("TP_DK_SAR_A")
    val SAR: String,
    @SerializedName("TP_DK_SEK_A")
    val SEK: String,
    @SerializedName("TP_DK_USD_A")
    val USD: String,


){
    // para birimine göre belirli kuru döndürür
    fun getRateName(selectedRate:String):String?{
        return when(selectedRate){
            "AED" -> AED
            "AUD" -> AUD
            "AZN" -> AZN
            "BGN" -> BGN
            "CAD" -> CAD
            "CHF" -> CHF
            "CNA" -> CNA
            "DKK" -> DKK
            "EUR" -> EUR
            "GBP" -> GBP
            "IRR" -> IRR
            "JPY" -> JPY
            "KRW" -> KRW
            "KWD" -> KWD
            "NOK" -> NOK
            "PKR" -> PKR
            "QAR" -> QAR
            "RON" -> RON
            "RUB" -> RUB
            "SAR" -> SAR
            "SEK" -> SEK
            "USD" -> USD
            else -> {null}
        }
    }
}