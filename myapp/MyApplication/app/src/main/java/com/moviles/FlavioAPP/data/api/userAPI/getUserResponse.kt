package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class getUserResponse (
    @SerializedName("idUser")
    val idUser:UUID
)