package com.example.kartotekaapp_v3.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "companies"
)
data class FavoriteCompanies(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    val companyName: String,

    val companyId: String,
): Parcelable
