package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName

data class getUserRequest (
    @SerializedName("token")
    val token:String = "",
)