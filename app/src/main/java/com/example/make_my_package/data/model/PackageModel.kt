package com.example.make_my_package.data.model

data class PackageModel(
    val _id: String,
    val item_taxes: List<Int>,
    val name: List<String>,
    val price: Int,
    val specifications: List<Specification>
)