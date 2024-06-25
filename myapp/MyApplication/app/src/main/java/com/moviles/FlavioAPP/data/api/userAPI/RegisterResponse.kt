package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("message")
    val message:String = "",
)