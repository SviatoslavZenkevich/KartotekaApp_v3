package com.example.kartotekaapp_v3.room

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies")

    fun readAllData(): LiveData<List<FavoriteCompanies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCompanies: FavoriteCompanies)

    @Query("DELETE FROM companies WHERE companyId = :companyId")
    suspend fun delete (companyId : String):Int

    @Query("DELETE FROM companies")
    suspend fun deleteAllCompanies():Int
}