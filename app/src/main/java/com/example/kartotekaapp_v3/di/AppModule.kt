package com.example.kartotekaapp_v3.di


import android.content.Context
import androidx.room.Room
import com.example.kartotekaapp_v3.network.BASE_URL
import com.example.kartotekaapp_v3.network.CompanyApi
import com.example.kartotekaapp_v3.room.CompanyDao
import com.example.kartotekaapp_v3.room.CompanyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.text.Typography.dagger

@Module
@InstallIn (SingletonComponent:: class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): CompanyApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(CompanyApi::class.java)


    @Provides
    @Singleton
    fun provideCompanyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            CompanyDatabase::class.java,
            "company_database"
        ).build()

//    @Provides
//    @Singleton
//    fun provideCompanyDao(appDatabase: CompanyDatabase): CompanyDao {
//        return appDatabase.getCompanyDao()
//    }
}