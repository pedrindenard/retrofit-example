package com.app.myapplication.feature.domain.model

import java.io.Serializable

data class Company(
    val id: Long,
    val name: String,
    val logo: String,
    val country: String
) : Serializable