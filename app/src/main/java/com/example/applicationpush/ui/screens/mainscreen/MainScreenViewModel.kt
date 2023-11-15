package com.example.applicationpush.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationpush.data.ApiService
import com.example.applicationpush.domain.models.Currency
import com.example.applicationpush.utils.Constans
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreenViewModel: ViewModel() {

    val currency = MutableStateFlow<Currency?>(null)

    init {
        initRetrofit()
    }

    private fun initRetrofit() {
        viewModelScope.launch(Dispatchers.IO) {
            flow<Currency?> {
                val converter = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constans.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(converter))
                    .build()

                val service = retrofit.create(ApiService::class.java)


            }.collectLatest {
                currency.value = it
            }
        }

    }

}