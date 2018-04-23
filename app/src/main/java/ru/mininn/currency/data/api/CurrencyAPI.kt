package ru.mininn.currency.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.mininn.currency.data.Constants
import ru.mininn.currency.data.model.Currency
import ru.mininn.currency.data.model.Responce
import rx.Observable

interface CurrencyAPI {

    @GET("stocks.json")
    fun getCurrencyList():
            Observable<Responce>

    companion object {
        fun create(): CurrencyAPI {
            val  gson = GsonBuilder().registerTypeAdapter(Currency::class.java, CurrencyDeserializer())
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJavaCallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create(gson.create()))
                    .baseUrl(
                            Constants.BASE_URL)
                    .build()

            return retrofit.create(CurrencyAPI::class.java)
        }
    }
}