package com.example.data.storage

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class Storage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IStorage {
    override fun saveCurrency(currency: String?) {
        sharedPreferences.edit().putString("currency", currency).apply()
    }

    override fun getCurrency(): String? {
        return sharedPreferences.getString("currency", null)
    }
}