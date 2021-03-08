package com.pascal.tictactoe.rooms

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="data_winner" )
data class DataWinner(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name ="player_name")
       val playerName : String
) {

}