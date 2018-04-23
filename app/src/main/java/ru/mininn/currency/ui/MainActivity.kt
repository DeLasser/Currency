package ru.mininn.currency.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ru.mininn.currency.R
import ru.mininn.currency.data.CurrencyLiveData
import ru.mininn.currency.data.model.Responce

class MainActivity : AppCompatActivity(), Observer<Responce> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        CurrencyLiveData.instance.observeForever(this)
    }

    override fun onStop() {
        super.onStop()
        CurrencyLiveData.instance.removeObserver(this)
    }

    override fun onChanged(t: Responce?) {
        for (item in t!!.currencyList) {
            Log.d("asdasd", item.name + " " + item.amount)
        }

    }
}
