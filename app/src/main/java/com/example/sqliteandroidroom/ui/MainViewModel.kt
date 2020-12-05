package com.example.sqliteandroidroom.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqliteandroidroom.models.Gasto
import com.example.sqliteandroidroom.services.Repository
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository): ViewModel() {
    val listaGastos = MutableLiveData<List<Gasto>>()

    fun addGastoDB(gasto: Gasto){
        viewModelScope.launch {
           listaGastos.value = repository.addGastoTask(gasto)
        }
    }
}