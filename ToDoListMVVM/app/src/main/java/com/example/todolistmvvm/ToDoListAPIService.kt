package com.example.todolistmvvm

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ToDoListAPIService {

    private val apiClient = OkHttpClient().newBuilder().build()
    private const val url = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit  =  Retrofit.Builder()
                                            .client(apiClient)
                                            .baseUrl(url)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                            .build()

    val api: ToDoAPI = getRetrofit().create(ToDoAPI::class.java)
    var arras: Array<Int> = Array(5, { i->(i*0)})
}