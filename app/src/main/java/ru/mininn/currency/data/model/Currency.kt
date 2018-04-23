package ru.mininn.currency.data.model

import com.google.gson.annotations.Expose

class Currency(@Expose val name: String,
               @Expose val volume: Int,
               @Expose val amount: Double)