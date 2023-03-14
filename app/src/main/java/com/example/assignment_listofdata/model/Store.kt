package com.example.assignment_listofdata.model

import java.io.Serializable

data class Store(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Serializable