package com.graphqlexample.graphqlexample.model

data class StringOfPearls(
    val date: String,
    val total: Int
)

data class Pearl(
    val notes: String,
    val turd: String,
    val name: String
)

data class NewPearl(
    val date: String,
    val notes: String,
    val turd: String,
    val name: String
)

