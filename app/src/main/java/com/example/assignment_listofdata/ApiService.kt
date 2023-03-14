package com.example.assignment_listofdata

import com.example.assignment_listofdata.model.Store
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {

    @GET("products?limit=10")
    suspend fun getStoreList() : List<Store>

    companion object {
         var apiService : ApiService? = null
        fun getInstance() : ApiService {
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }

    }
}