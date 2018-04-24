package ru.mininn.currency.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ru.mininn.currency.R
import ru.mininn.currency.data.CurrencyLiveData
import ru.mininn.currency.data.model.Responce
import ru.mininn.currency.ui.main.adapter.CurrencyAdapter

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
        if (responce?.currencyList != null) {
            adapter!!.updateData(responce.currencyList)
        } else if (responce?.error != null) {
            showError(getString(R.string.error_connection))
        }

    }

    private fun initRecycler() {
        adapter = CurrencyAdapter(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_update) {
            CurrencyLiveData.instance.startDataUpdating()
        }
        return true
    }

    private fun showError(message: String) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_INDEFINITE)
                .show()
    }

}
