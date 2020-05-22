package com.abhishek.digital_library.model

data class bookItems(
    val name: String,
    val url: String
)

data class DatabaseCourse(
    val name: String,
    val url: String
) {
    constructor() : this("", "")
}