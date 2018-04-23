package ru.mininn.currency.data

import android.arch.lifecycle.LiveData
import ru.mininn.currency.data.api.CurrencyAPI
import ru.mininn.currency.data.model.Responce
import rx.android.schedulers.AndroidSchedulers
import rx.internal.operators.EmptyObservableHolder
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class CurrencyLiveData : LiveData<Responce>() {
    var isActive: Boolean = false

    private object Holder { val INSTANCE = CurrencyLiveData() }

    companion object {
        val instance: CurrencyLiveData by lazy { Holder.INSTANCE }
    }

    override fun onInactive() {
        super.onInactive()
        isActive = false

    }

    override fun onActive() {
        super.onActive()
        isActive = true
        startDataUpdating()
    }

    fun startDataUpdating() {
        CurrencyAPI.create()
                .getCurrencyList()
                .repeatWhen { completed ->
                    if (isActive) {
                        return@repeatWhen completed.delay(5, TimeUnit.SECONDS)
                    } else {
                        return@repeatWhen EmptyObservableHolder.instance<Any>()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> value = result })
    }
}