package com.example.sqliteandroidroom.services

import com.example.sqliteandroidroom.dao.GastoDAO
import com.example.sqliteandroidroom.models.Gasto


interface Repository{

    suspend fun addGastoTask(gasto: Gasto)

    suspend fun getAllGastosTask(): List<Gasto>

}

class RepositoryImpl(val gastoDAO: GastoDAO):Repository{
    override suspend fun addGastoTask(gasto: Gasto) {
        gastoDAO.addGasto(gasto)
    }

    override suspend fun getAllGastosTask() = gastoDAO.getAllGastos()

}