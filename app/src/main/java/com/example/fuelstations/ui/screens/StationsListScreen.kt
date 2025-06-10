package com.example.fuelstations.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fuelstations.data.FuelStationsRepository
import com.example.fuelstations.model.FuelStation

@Composable
fun StationsListScreen() {
    val context = LocalContext.current
    val repository = remember { FuelStationsRepository(context) }
    val stations = remember { repository.getFuelStations() }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(stations) { station ->
            StationListItem(station = station)
        }
    }
}

@Composable
fun StationListItem(station: FuelStation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${station.station} - ${station.brand}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Регион: ${station.region}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Адрес: ${station.address}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
