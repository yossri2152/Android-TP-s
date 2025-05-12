package com.example.tplogin

import com.example.tplogin.data.ImageType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    fun getData(): Call<List<User>>


    @GET("photos?client_id=c4Va-NQYO4wQGXiEAGO0eNlWQwo1qjn3RqwR1OaE_mc")
    fun getImage(): Call<List<ImageType>>



}


