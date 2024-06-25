package com.moviles.FlavioAPP.model

import java.util.UUID

data class User (
    val idUser: UUID,
    val username:String = "",
    val name:String = "",
    val lastname:String = "",
    val email:String = "",
    val phone:String = "",
    val password:String = "",
)