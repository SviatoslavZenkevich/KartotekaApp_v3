package com.example.kartotekaapp_v3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [FavoriteCompanies::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {

    abstract fun getCompanyDao(): CompanyDao

    companion object {
        @Volatile
        private var  instance: CompanyDatabase? = null
        private val LOCK = Any ()

        operator fun invoke (context: Context) = instance?: synchronized (LOCK) {
            instance ?: getDatabase(context).also { instance = it }
        }
        fun getDatabase (context: Context) : CompanyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CompanyDatabase:: class.java,
                "company_database"
            ).build()

        }
    }

}

