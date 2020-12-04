package com.example.sqliteandroidroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gastos")
data class Gasto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nome: String,
    var valor: Double
)