package com.moviles.FlavioAPP.model

import kotlinx.serialization.Serializable
import java.util.UUID

data class Book (
    val idBook: UUID,
    val title:String = "",
    val author:String = "",
    val genre:String = "",
    val description:String = "",
    val rating:Float = 0.0F,
)