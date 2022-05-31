package com.example.cryptotrack.viewmodels

import androidx.lifecycle.ViewModel
import com.example.data.storage.IStorage
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val storage: IStorage
): ViewModel() {

    fun saveCurrency(currency: String) {
        storage.saveCurrency(currency)
    }

    fun getCurrency(): String? {
        return storage.getCurrency();
    }

}