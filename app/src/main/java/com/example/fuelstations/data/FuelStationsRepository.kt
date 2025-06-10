package com.example.fuelstations.data

import android.content.Context
import com.example.fuelstations.model.FuelStation
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Types

class FuelStationsRepository(private val context: Context) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getFuelStations(): List<FuelStation> {
        val jsonString = context.assets.open("fuel_stations.json").bufferedReader().use { it.readText() }
        val type = Types.newParameterizedType(List::class.java, FuelStation::class.java)
        val adapter = moshi.adapter<List<FuelStation>>(type)
        return adapter.fromJson(jsonString) ?: emptyList()
    }
}
