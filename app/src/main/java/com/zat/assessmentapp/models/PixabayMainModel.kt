package com.zat.assessmentapp.models

data class PixabayMainModel(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)