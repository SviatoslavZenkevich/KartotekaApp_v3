package com.example.kartotekaapp_v3.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.kartotekaapp_v3.room.CompanyDao
import com.example.kartotekaapp_v3.room.CompanyDatabase
import com.example.kartotekaapp_v3.room.CompanyRepository
import com.example.kartotekaapp_v3.room.FavoriteCompanies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CompanyViewModel(application: Application) : AndroidViewModel(application) {

    private val companyDao: CompanyDao
    private val companyDatabase: CompanyDatabase
    val readAllData: LiveData<List<FavoriteCompanies>>
    private val repository: CompanyRepository
    init {
        companyDatabase = CompanyDatabase.getDatabase(application)
        companyDao = companyDatabase.companyDao()

        repository = CompanyRepository(companyDao)
        readAllData = repository.readAllData
    }

//    fun insertCompany(unp: String, companyName: String) {
//        val company = FavoriteCompanies(null, unp, companyName)
//        viewModelScope.launch {
//            companyDao.insert(company)
//        }
//    }
    fun addCompany(favoriteCompanies: FavoriteCompanies) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavorite(favoriteCompanies)
        }
    }
    fun deleteFavoriteCompany(favoriteCompanies: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavorite(favoriteCompanies)
        }
    }

    fun deleteAllCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }
}



//class CompanyViewModel(private val repository: CompanyRepository) : ViewModel() {
//    init {
//        getSavedCompanies()
//    }
//
//    fun getSavedCompanies() = viewModelScope.launch(Dispatchers.IO) {
//        val res = repository.getFavoriteCompanies()
//        repository.getFavoriteCompanies()
//    }
//
//}