package com.example.sqliteandroidroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqliteandroidroom.dao.GastoDAO
import com.example.sqliteandroidroom.models.Gasto

@Database(entities = [Gasto::class], version = 1)
abstract class AppDataBase: RoomDatabase(){

    abstract fun gastoDAO(): GastoDAO

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "app_data_base.db"
        ).build()
    }
}