package com.example.applicationpush.data

import com.example.applicationpush.domain.models.Currency
import com.example.applicationpush.utils.Constans
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {


    @GET(Constans.ENDPOINT_LATEST)
    fun getCurrency(): Call<Currency?>?

}