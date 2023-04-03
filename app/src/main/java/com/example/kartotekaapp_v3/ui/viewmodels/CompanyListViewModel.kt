package com.example.kartotekaapp_v3.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kartotekaapp_v3.room.CompanyDao
import com.example.kartotekaapp_v3.room.CompanyDatabase
import com.example.kartotekaapp_v3.room.CompanyRepository
import com.example.kartotekaapp_v3.room.FavoriteCompanies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyListViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<FavoriteCompanies>>
    private val repository: CompanyRepository

    init {
        val companyDao = CompanyDatabase.getDatabase(
            application
        ).companyDao()
        repository = CompanyRepository(companyDao)
        readAllData = repository.readAllData
    }

    fun deleteAllCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }
}




//class CompanyListViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val companyDao = CompanyDatabase.getDatabase(application).companyDao()
//
//    val companyList: List<FavoriteCompanies> = companyDao.getAllCompanies()
//}


//class CompanyListViewModel(private val repository: CompanyRepository) : ViewModel() {
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



