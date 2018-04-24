package ru.mininn.currency.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.mininn.currency.data.CurrencyLiveData
import ru.mininn.currency.data.model.Responce
import ru.mininn.currency.ui.main.MainActivity

class SplashScreen : AppCompatActivity(), Observer<Responce> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (CurrencyLiveData.instance.value != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            CurrencyLiveData.instance.observeForever(this)
        }
    }

    override fun onChanged(t: Responce?) {
        if (t != null) {
            CurrencyLiveData.instance.removeObserver(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}