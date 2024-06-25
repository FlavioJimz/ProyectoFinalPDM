package com.moviles.FlavioAPP.model

data class Review (
    val user:User,
    val book:Book,
    val rating:Float = 0.0F,
    val comment:String = "",
)