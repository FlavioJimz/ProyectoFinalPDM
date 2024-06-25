package com.moviles.FlavioAPP.data.api.userAPI

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserRequest(
    val user_code: UUID,
    val book_code: UUID
)



