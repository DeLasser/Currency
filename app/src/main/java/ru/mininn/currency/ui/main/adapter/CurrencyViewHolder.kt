package ru.mininn.currency.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_currency.view.*
import ru.mininn.currency.data.model.Currency
import java.util.*

class CurrencyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(currency: Currency?) {
        itemView.name.text = currency!!.name
        itemView.volume.text = currency.volume.toString()
        itemView.amount.text = String.format(Locale.getDefault(), "%.2f", currency.amount)
    }
}