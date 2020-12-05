package com.example.sqliteandroidroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sqliteandroidroom.models.Gasto

@Dao
interface GastoDAO{

    //insert
    @Insert
    suspend fun addGasto(gasto: Gasto)

    //select
    @Query("SELECT * FROM gastos")
    suspend fun getAllGastos():List<Gasto>

    //update

    //delete
}