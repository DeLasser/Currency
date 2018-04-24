package ru.mininn.currency.ui.main

import android.os.Bundle
import ru.mininn.currency.data.model.Responce

interface MainContract {

    interface View {
        fun updateData(responce: Responce?)
    }

    interface Presenter {
        fun onCreate(savedInstanceState: Bundle?)

        fun onStart()

        fun onStop()

        fun requestNewData()
    }
}