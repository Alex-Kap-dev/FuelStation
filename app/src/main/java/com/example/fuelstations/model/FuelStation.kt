package com.example.fuelstations.model

data class FuelStation(
    val station: String,          // Код станции (например "BY1")
    val brand: String,            // Бренд АЗС
    val latitude: Double,         // Географическая широта
    val longitude: Double,        // Географическая долгота
    val region: String,           // Область
    val address: String           // Полный адрес станции
)
