package ru.mininn.currency.data

import android.arch.lifecycle.LiveData
import ru.mininn.currency.data.api.CurrencyAPI
import ru.mininn.currency.data.model.Responce
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class CurrencyLiveData : LiveData<Responce>() {
    private var subscription: Subscription? = null

    private object Holder {
        val INSTANCE = CurrencyLiveData()
    }

    companion object {
        val instance: CurrencyLiveData by lazy { Holder.INSTANCE }
    }

    override fun onInactive() {
        super.onInactive()
        unsubscribe()
    }

    override fun onActive() {
        super.onActive()
        startDataUpdating()
    }

    fun startDataUpdating() {
        unsubscribe()
        subscription = CurrencyAPI.create()
                .getCurrencyList()
                .repeatWhen { completed ->
                    return@repeatWhen completed.delay(15, TimeUnit.SECONDS)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            value = result
                        }, { error ->
                    val respounce = Responce(null, null)
                    respounce.error = error.message
                    value = respounce
                })
    }

    private fun unsubscribe() {
        if (subscription != null) {
            subscription!!.unsubscribe()
        }
    }

}