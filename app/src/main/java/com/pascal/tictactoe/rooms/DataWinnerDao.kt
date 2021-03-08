package com.pascal.tictactoe.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataWinnerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(winner: DataWinner)

    @Query("SELECT * FROM data_winner ORDER BY id asc")
    fun getAlldata(): LiveData<List<DataWinner>>

    @Query("DELETE FROM data_winner")
    suspend fun resetHistory()
}