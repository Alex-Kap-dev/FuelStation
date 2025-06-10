package com.example.fuelstations.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.fuelstations.data.FuelStationsRepository
import com.example.fuelstations.model.FuelStation
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen() {
    val context = LocalContext.current
    val repository = remember { FuelStationsRepository(context) }
    val stations = remember { repository.getFuelStations() }
    var selectedStation by remember { mutableStateOf<FuelStation?>(null) }

    // OSM требует настройки конфигурации перед использованием
    LaunchedEffect(key1 = Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // AndroidView, который будет содержать OSM карту
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    controller.setZoom(7.0)

                    // Установим начальную позицию в центр Беларуси
                    controller.setCenter(GeoPoint(53.9, 27.6))

                    // Добавим маркеры для каждой станции
                    stations.forEach { station ->
                        val marker = Marker(this).apply {
                            position = GeoPoint(station.latitude, station.longitude)
                            title = station.station
                            snippet = station.brand
                            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            setOnMarkerClickListener { marker, mapView ->
                                selectedStation = station
                                true
                            }
                        }
                        overlays.add(marker)
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { mapView ->
                // Обновление карты, если необходимо
            }
        )

        // Показываем информацию о выбранной станции
        selectedStation?.let { station ->
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                StationDetails(station = station, onClose = { selectedStation = null })
            }
        }
    }

    // Управление жизненным циклом MapView
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
                }
                else -> { /* no-op */ }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun StationDetails(station: FuelStation, onClose: () -> Unit) {
    Surface(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${station.station} - ${station.brand}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Регион: ${station.region}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Адрес: ${station.address}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Координаты: ${station.latitude}, ${station.longitude}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )

            Button(
                onClick = onClose
            ) {
                Text("Закрыть")
            }
        }
    }
}

