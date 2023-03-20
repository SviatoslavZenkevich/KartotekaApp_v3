package com.example.kartotekaapp_v3.fragments

import android.app.Application
import androidx.lifecycle.*
import com.example.kartotekaapp_v3.room.CompanyDatabase
import com.example.kartotekaapp_v3.room.CompanyRepository
import com.example.kartotekaapp_v3.room.FavoriteCompanies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CompanyViewModel(application: Application) : AndroidViewModel(application) {

    //    val liveData = MutableLiveData <FavoriteCompanies> ()
    val allCompanies: LiveData<List<FavoriteCompanies>>

    val repository: CompanyRepository

    init {
        val dao = CompanyDatabase.getDatabase(application).getCompanyDao()
        repository = CompanyRepository(dao)
        allCompanies = repository.allCompanies
    }

    fun deleteFavoriteCompany(favoriteCompanies: FavoriteCompanies) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(favoriteCompanies)
    }

    fun addFavoriteCompany(favoriteCompanies: FavoriteCompanies) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(favoriteCompanies)
        }

}