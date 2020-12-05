package com.example.sqliteandroidroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseHandler: DataBaseHandler = DataBaseHandler(this)
        val res = dataBaseHandler.getAllGastos()
        res.forEach {
            Log.i("MainActivity", it.toString())
        }

//        val res = dataBaseHandler.addGastoDataBase(Gasto(nome = "Comida", valor = 10.0))
//        Log.i("MainActivity", res.toString())

    }
}