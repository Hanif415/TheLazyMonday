package com.example.thelazymonday.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameNewsEntities")
data class GameNewsEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "thumb")
    val thumb: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "tag")
    val tag: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "key")
    val key: String,
)
