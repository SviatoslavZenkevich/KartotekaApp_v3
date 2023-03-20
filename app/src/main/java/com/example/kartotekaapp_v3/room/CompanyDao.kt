package com.example.kartotekaapp_v3.room

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies")
    fun getAllCompanies(): LiveData<List<FavoriteCompanies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCompanies: FavoriteCompanies)

    @Delete
    suspend fun delete(favoriteCompanies: FavoriteCompanies)
}