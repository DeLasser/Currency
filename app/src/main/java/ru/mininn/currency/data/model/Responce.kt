package ru.mininn.currency.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Responce(@SerializedName("stock") @Expose var currencyList: List<Currency>?,
               @SerializedName("as_of") @Expose var date: String?) {
    var error: String? = null
}