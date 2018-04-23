package ru.mininn.currency.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ru.mininn.currency.R
import ru.mininn.currency.data.model.Responce
import ru.mininn.currency.ui.adapter.CurrencyAdapter

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter by lazy { MainPresenter(this) }
    private val recyclerView: RecyclerView by lazy { this.recycler_view }
    private var adapter: CurrencyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        presenter.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun updateData(responce: Responce?) {
        adapter!!.updateData(responce!!.currencyList)
        Log.d("asdasd", adapter!!.itemCount.toString())
    }

    private fun initRecycler() {
        adapter = CurrencyAdapter(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

}
