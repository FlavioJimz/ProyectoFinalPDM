package com.moviles.FlavioAPP

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.moviles.FlavioAPP.data.api.ApiService
import com.moviles.FlavioAPP.data.api.RetrofitClient
import com.moviles.FlavioAPP.navigation.AppNav
import com.moviles.FlavioAPP.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = RetrofitClient.create(ApiService::class.java)
        val viewModelFactory = MainViewModelFactory(application, apiService)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppNav(viewModel)
                }
            }
        }
    }
}