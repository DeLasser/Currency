package ru.mininn.currency.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import ru.mininn.currency.data.CurrencyLiveData
import ru.mininn.currency.data.model.Responce

class MainPresenter(var view: MainContract.View) : MainContract.Presenter, Observer<Responce> {
    override fun onChanged(t: Responce?) {
        view.updateData(t)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (CurrencyLiveData.instance.value != null) {
            view.updateData(CurrencyLiveData.instance.value)
        }
    }

    override fun onStart() {
        CurrencyLiveData.instance.observeForever(this)
    }

    override fun onStop() {
        CurrencyLiveData.instance.removeObserver(this)
    }

    override fun requestNewData() {
        CurrencyLiveData.instance.startDataUpdating()
    }

}
