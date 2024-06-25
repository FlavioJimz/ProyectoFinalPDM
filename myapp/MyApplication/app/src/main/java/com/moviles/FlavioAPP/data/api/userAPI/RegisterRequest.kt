package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    val username:String = "",
    @SerializedName("name")
    val name:String = "",
    @SerializedName("lastname")
    val lastname:String = "",
    @SerializedName("email")
    val email:String = "",
    @SerializedName("phone")
    val phone:String = "",
    @SerializedName("password")
    val password:String = "",
)
