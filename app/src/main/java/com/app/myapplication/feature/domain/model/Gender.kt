package com.app.myapplication.feature.domain.model

import java.io.Serializable

data class Gender(
    val id: Int,
    val name: String,
    val image: String
) : Serializable