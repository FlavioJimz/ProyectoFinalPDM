package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username:String = "",
    @SerializedName("password")
    val password:String = "",
)
