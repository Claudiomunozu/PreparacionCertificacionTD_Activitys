package com.example.preparacioncertificaciontdactivitys.data.local.entities

import androidx.room.PrimaryKey

data class VideoGameDetail(
    @PrimaryKey
    val id: Long,
    val playtime: Long,
    val platform: String,
    val genres: String,
    val format: String,
    val price: Double,
    val lastPrice: Double,
    val delivery: Boolean,
)