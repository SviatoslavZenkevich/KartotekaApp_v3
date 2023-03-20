package com.example.kartotekaapp_v3.network

import com.example.kartotekaapp_v3.data.Company
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://statuspro.by/api/v1/"

interface CompanyApi {
    @GET("egr/{taxId}")
    fun getCompanyByTaxId(@Path("taxId") taxId: Int?): Call<Company>

    companion object {


        fun create() : CompanyApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CompanyApi::class.java)
        }
    }
}