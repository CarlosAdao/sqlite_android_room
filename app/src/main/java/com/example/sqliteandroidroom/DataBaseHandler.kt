package com.example.sqliteandroidroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DataBaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "mywallet"
        private val DATABASE_VERSION = 1

        //Nome da table
        private val TABLE_GASTOS = "gastos"

        //Nome das colunas
        private val KEY_ID = "id"
        private val KEY_NOME = "nome"
        private val KEY_VALOR = "valor"

    }

    //Método de criação do banco de dados
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_GASTOS = ("CREATE TABLE $TABLE_GASTOS(" +
                "$KEY_ID INTEGER PRIMARY KEY, " +
                "$KEY_NOME TEXT, " +
                "$KEY_VALOR REAL" +
                ")")

        db?.execSQL(CREATE_TABLE_GASTOS)
    }

    //Método de atualização do banco de dados
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_GASTOS")
    }

    //INSERT
    fun addGasto(gasto: Gasto): Long{
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(KEY_ID, gasto.id)
        content.put(KEY_NOME, gasto.nome)
        content.put(KEY_VALOR, gasto.valor)

        val res  = db.insert(TABLE_GASTOS, null, content)
        db.close()

        return res
    }

    //SELECT
    fun getAllGastos(): List<Gasto>{
        var listGastos = ArrayList<Gasto>()
        val query = "SELECT * FROM $TABLE_GASTOS"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if(cursor.moveToFirst()){
                do {
                   val id = cursor.getInt(cursor.getColumnIndex("id"))
                   val nome = cursor.getString(cursor.getColumnIndex("nome"))
                   val valor = cursor.getDouble(cursor.getColumnIndex("valor"))
                   listGastos.add(Gasto(id, nome, valor))
                }while (cursor.moveToNext())
            }
        }catch (e: SQLException){
            Log.e("ERRO", e.toString())
        }
        return listGastos
    }

}