package com.pascal.tictactoe.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataWinner::class], version = 1, exportSchema = false)
abstract class DataWinnerDatabase : RoomDatabase() {

    abstract fun dataWinnerDao(): DataWinnerDao

    companion object{
        @Volatile
        private var INSTANCE: DataWinnerDatabase? = null

        fun getDatabsase(context: Context) : DataWinnerDatabase {
            val tempInstance =  INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    DataWinnerDatabase::class.java,
                "history_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}