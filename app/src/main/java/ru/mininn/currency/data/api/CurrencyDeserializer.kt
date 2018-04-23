package ru.mininn.currency.data.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.mininn.currency.data.model.Currency
import java.lang.reflect.Type

class CurrencyDeserializer: JsonDeserializer<Currency> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Currency? {
        if (json != null && json.asJsonObject.get("name") != null) {
            return Currency(
                    json.asJsonObject.get("name").asString,
                    json.asJsonObject.get("volume").asInt,
                    json.asJsonObject.get("price").asJsonObject.get("amount").asDouble

            )
        }
        return null
    }
}