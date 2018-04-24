package ru.mininn.currency.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.mininn.currency.R
import ru.mininn.currency.data.model.Currency

class CurrencyAdapter(var data: List<Currency>?) : RecyclerView.Adapter<CurrencyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_currency, parent, false))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder?, position: Int) {
        holder!!.bind(data!![position])
    }

    fun updateData(data: List<Currency>?) {
        this.data = data
        notifyDataSetChanged()
    }
}