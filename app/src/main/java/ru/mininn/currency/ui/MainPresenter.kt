package ru.mininn.currency.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
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
        Log.d("asdasd", "onStart")
        CurrencyLiveData.instance.observeForever(this)
    }

    override fun onStop() {
        Log.d("asdasd", "onStop")
        CurrencyLiveData.instance.removeObserver(this)
    }

    override fun requestNewData() {
        CurrencyLiveData.instance.startDataUpdating()
    }

}
