package com.example.data.storage

interface IStorage {
    fun saveCurrency(currency: String?)
    fun getCurrency(): String?
}