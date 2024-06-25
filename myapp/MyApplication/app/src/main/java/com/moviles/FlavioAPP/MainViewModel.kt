package com.moviles.FlavioAPP

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.FlavioAPP.data.api.ApiService
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application, private val apiService: ApiService) : AndroidViewModel(application) {

    private val loginResponseState = mutableStateOf<LoginResponse?>(null)
    val loginResponse : State<LoginResponse?> = loginResponseState

    private val registerResponseState = mutableStateOf<RegisterResponse?>(null)
    val registerResponse : State<RegisterResponse?> = registerResponseState

    private val bookState = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> get() = bookState

    private val favoriteBookState = MutableStateFlow<List<Book>>(emptyList())
    val favoriteBook: StateFlow<List<Book>> get() = favoriteBookState

    private val bookDetailState = MutableStateFlow<Book?>(null)
    val bookDetail: StateFlow<Book?> = bookDetailState

    private val userInfoState = MutableStateFlow<User?>(null)
    val userInfo: StateFlow<User?> = userInfoState

    fun login(username: String, password: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            val loginRequest = LoginRequest(username, password)
            apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful){
                        loginResponseState.value = response.body()
                    }
                    else{
                        Log.d("error", "error en la peticion")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("error", "error de server")
                }
            })
        }
    }

    fun register(username: String, name: String, surname : String, email: String, phone: String, password: String){
        val registerRequest = RegisterRequest(username,name,surname,email,phone,password)
        apiService.register(registerRequest).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    registerResponseState.value = response.body()
                }else{
                    registerResponseState.value = RegisterResponse("Fallo el registrar un usuario")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                registerResponseState.value = RegisterResponse("Error de servidor")
            }
        })
    }

    fun getAllBooks(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.getAllBooks("Bearer $token").enqueue(object : Callback<List<Book>> {
                override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                    if (response.isSuccessful) {
                        bookState.value = response.body() ?: emptyList()
                    } else {
                        Log.d("error", "error en la peticion")
                    }
                }

                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    Log.d("error", "error de server")
                }
            })
        }
    }

    fun getFavoriteBooks(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            apiService.getFavoriteBooks("Bearer $token").enqueue(object : Callback<List<Book>> {
                override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                    if (response.isSuccessful) {
                        favoriteBookState.value = response.body() ?: emptyList()
                    } else {
                        Log.d("error", "error en la peticion")
                    }
                }

                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    Log.d("error", "error de server")
                }
            })
        }
    }

    fun getBookByTitle(token: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.getBookByTitle("Bearer $token", title).enqueue(object : Callback<Book> {
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                    if (response.isSuccessful) {
                        bookDetailState.value = response.body()
                    } else {
                        Log.d("error", "error en la peticion")
                    }
                }

                override fun onFailure(call: Call<Book>, t: Throwable) {
                    Log.d("error", "error de server")
                }
            })
        }
    }

    fun favoriteBook(token: String, userInfo: User, bookInfo: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteRequest = FavoriteRequest(userInfo,bookInfo)
            apiService.addBookToFavorites(favoriteRequest).enqueue(object : Callback<FavoriteResponse> {
                override fun onResponse(call: Call<FavoriteResponse>, response: Response<FavoriteResponse>) {
                    if (response.isSuccessful) {
                        Log.d("success", "Libro agregado a favoritos")
                        getFavoriteBooks(token) // Actualizar la lista de favoritos
                    } else {
                        Log.d("error", "Error al agregar libro a favoritos: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                    Log.d("error", "Error de servidor al agregar libro a favoritos", t)
                }
            })
        }
    }

    fun getUserInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userInfoRequest = getUserRequest(token)
            apiService.getUserID(userInfoRequest).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        userInfoState.value = response.body()
                    } else {
                        Log.d("error", "Error en la petición")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("error", "Error de servidor")
                }
            })
        }
    }

}