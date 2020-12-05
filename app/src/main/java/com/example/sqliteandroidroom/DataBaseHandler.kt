package com.example.sqliteandroidroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "app_data_base"
        private val DATABASE_VERSION = 1

        //Constantes tabela gasto
        private val TABLE_GASTO = "gastos"
        private val KEY_ID = "id"
        private val KEY_NOME = "nome"
        private val KEY_VALOR = "valor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_GASTO = "CREATE TABLE $TABLE_GASTO(\n" +
                "          $KEY_ID INTEGER PRIMARY KEY,\n" +
                "          $KEY_NOME TEXT,\n" +
                "          $KEY_VALOR REAL\n" +
                "        )"
        db?.execSQL(CREATE_TABLE_GASTO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTO)
    }

    //insert
    fun addGastoDataBase(gasto: Gasto):Long{
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(KEY_NOME, gasto.nome)
        content.put(KEY_VALOR, gasto.valor)
        val res = db.insert(TABLE_GASTO, null, content)
        db.close()

        return res
    }

    //Select
    fun getAllGastos(): List<Gasto>{
        var listGastos = ArrayList<Gasto>()
        val query = "SELECT * FROM $TABLE_GASTO"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(query, null)
            if(cursor.moveToFirst()){
                do {
                    var id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    var nome = cursor.getString(cursor.getColumnIndex(KEY_NOME))
                    var valor = cursor.getDouble(cursor.getColumnIndex(KEY_VALOR))

                    listGastos.add(Gasto(id, nome, valor))
                }while (cursor.moveToNext())
            }
        }catch (e: SQLException){
            Log.e("DataBaseHandler", e.toString())
        }
        return listGastos
    }
}