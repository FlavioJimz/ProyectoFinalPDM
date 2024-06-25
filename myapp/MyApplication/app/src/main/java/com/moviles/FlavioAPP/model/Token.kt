package com.moviles.FlavioAPP.model

import java.util.Date

data class Token (
    val token:String = "",
    val creationDate:Date,
    val active:Boolean,
    val user:User,
)