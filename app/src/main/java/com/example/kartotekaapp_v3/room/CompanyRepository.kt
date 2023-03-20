package com.example.kartotekaapp_v3.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import javax.inject.Inject

//class CompanyRepository @Inject constructor(
//
//    private val companyDao: CompanyDao
//) {
//
//
//    fun getFavoriteCompanies() = companyDao.getAllCompanies()
//    suspend fun insert(favoriteCompanies: FavoriteCompanies) =
//        companyDao.insert(favoriteCompanies = favoriteCompanies)
//
//    suspend fun delete(favoriteCompanies: FavoriteCompanies) =
//        companyDao.delete(favoriteCompanies = favoriteCompanies)
//}


class CompanyRepository(private val companyDao: CompanyDao) {

    val allCompanies: LiveData<List<FavoriteCompanies>> = companyDao.getAllCompanies()

    suspend fun insert(favoriteCompanies: FavoriteCompanies) {
        companyDao.insert(favoriteCompanies)
    }

    suspend fun delete(favoriteCompanies: FavoriteCompanies) {
        companyDao.delete(favoriteCompanies)
    }

}