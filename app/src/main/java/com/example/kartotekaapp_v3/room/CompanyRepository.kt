package com.example.kartotekaapp_v3.room

import androidx.lifecycle.LiveData

class CompanyRepository(

    private val companyDao: CompanyDao
) {

    val readAllData: LiveData<List<FavoriteCompanies>> = companyDao.readAllData()


    suspend fun addToFavorite(favoriteCompanies: FavoriteCompanies) =
        companyDao.insert(favoriteCompanies = favoriteCompanies)

    suspend fun deleteFromFavorite(companyId: String) =
        companyDao.delete(companyId)


    suspend fun deleteAllFavorite() =
        companyDao.deleteAllCompanies()
}

