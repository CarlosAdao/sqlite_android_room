package com.example.sqliteandroidroom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqliteandroidroom.R
import com.example.sqliteandroidroom.database.AppDataBase
import com.example.sqliteandroidroom.models.Gasto
import com.example.sqliteandroidroom.services.Repository
import com.example.sqliteandroidroom.services.RepositoryImplementation

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDataBase
    private  lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDB()
        repository = RepositoryImplementation(db.gastoDAO())

        val viewModel by viewModels<MainViewModel> {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MainViewModel(repository)  as T
                }
            }
        }

        viewModel.addGastoDB(Gasto(nome = "Transporte", valor = 15.0))

        viewModel.listaGastos.observe(this){
            it.forEach {
                Log.i("MainActivity", it.toString())
            }
        }
    }

    fun initDB(){
        db = AppDataBase.invoke(this)
    }
}