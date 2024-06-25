package com.moviles.FlavioAPP.data.api

import com.moviles.FlavioAPP.data.api.bookAPI.FavoriteRequest
import com.moviles.FlavioAPP.data.api.bookAPI.FavoriteResponse
import com.moviles.FlavioAPP.data.api.userAPI.LoginRequest
import com.moviles.FlavioAPP.data.api.userAPI.LoginResponse
import com.moviles.FlavioAPP.data.api.userAPI.RegisterRequest
import com.moviles.FlavioAPP.data.api.userAPI.RegisterResponse
import com.moviles.FlavioAPP.data.api.userAPI.UserRequest
import com.moviles.FlavioAPP.data.api.userAPI.getUserRequest
import com.moviles.FlavioAPP.data.api.userAPI.getUserResponse
import com.moviles.FlavioAPP.model.Book
import com.moviles.FlavioAPP.model.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @POST("auth/signup")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("book/all")
    fun getAllBooks(@Header("Authorization") token: String): Call<List<Book>>

    @GET("book/{title}")
    fun getBookByTitle(@Header("Authorization") token: String, @Path("title") title: String): Call<Book>

    @GET("favorite/book")
    fun getFavoriteBooks(@Header("Authorization") token: String): Call<List<Book>>

    @POST("favorite/")
    fun addBookToFavorites(@Body favoriteRequest: FavoriteRequest): Call<FavoriteResponse>

    @POST("auth/info")
    fun getUserID(@Body getUserRequest: getUserRequest) : Call<User>

}