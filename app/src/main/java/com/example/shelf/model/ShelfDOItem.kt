package com.example.shelf.model

data class ShelfDOItem(
    val alias: String,
    val hits: Int,
    val id: String,
    val image: String,
    val lastChapterDate: Int,
    val title: String
)