package com.example.kartotekaapp_v3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "companies"
)
data class FavoriteCompanies(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "companyId")
    val companyId: String,
)
