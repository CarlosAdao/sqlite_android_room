package com.example.sqliteandroidroom.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqliteandroidroom.models.Gasto
import com.example.sqliteandroidroom.services.Repository
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository): ViewModel() {

    val listGastos = MutableLiveData<List<Gasto>>()

    //Adicionar um novo gasto
    fun addNewGasto(gasto: Gasto){
        viewModelScope.launch {
            repository.addGastoTask(gasto)
        }
    }

    //Exibir a lista de Gastos
    fun getAllGastos(){
        viewModelScope.launch {
            listGastos.value = repository.getAllGastosTask()
        }
    }

}