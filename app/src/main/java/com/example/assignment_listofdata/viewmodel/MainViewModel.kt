package com.example.assignment_listofdata.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_listofdata.ApiService
import com.example.assignment_listofdata.model.Store
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var storeListResponse : List<Store> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getDataList() {
        viewModelScope.launch {
            var apiService = ApiService.getInstance()
            try{
                var storeList=apiService.getStoreList()
                storeListResponse = storeList
            }
            catch (e: Exception){
                errorMessage = e.message.toString()
                print("Error message::  $errorMessage");
            }
        }

    }
}