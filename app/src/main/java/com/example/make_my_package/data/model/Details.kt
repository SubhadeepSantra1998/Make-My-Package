package com.example.make_my_package.data.model

data class Details(
    val _id: String,
    val is_default_selected: Boolean,
    val name: List<String>,
    val price: Int,
    val sequence_number: Int,
    val specification_group_id: String,
    val unique_id: Int
)
