package com.moviles.FlavioAPP.data.api.bookAPI

import com.google.gson.annotations.SerializedName
import com.moviles.FlavioAPP.model.Book
import com.moviles.FlavioAPP.model.User

data class FavoriteRequest(
    @SerializedName("user")
    val user: User,
    @SerializedName("book")
    val book: Book,
    )

