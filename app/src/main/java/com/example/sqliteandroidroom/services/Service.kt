package com.example.sqliteandroidroom.services

import com.example.sqliteandroidroom.dao.GastoDAO
import com.example.sqliteandroidroom.models.Gasto

interface Repository{

    //inserts
    suspend fun addGastoTask(gasto: Gasto): List<Gasto>

    //selects
    suspend fun getAllGastosTask(): List<Gasto>

    //updates

    //deletes
}

class RepositoryImplementation(val gastoDAO: GastoDAO): Repository{
    override suspend fun addGastoTask(gasto: Gasto): List<Gasto> {
        gastoDAO.addGasto(gasto)
        return gastoDAO.getAllGastos()
    }

    override suspend fun getAllGastosTask() = gastoDAO.getAllGastos()

}
