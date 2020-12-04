package com.example.sqliteandroidroom.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.sqliteandroidroom.models.Gasto

interface GastoDAO{

    @Insert
    suspend fun addGasto(gasto: Gasto)

    @Query("SELECT * FROM gastos")
    suspend fun getAllGastos():List<Gasto>

}