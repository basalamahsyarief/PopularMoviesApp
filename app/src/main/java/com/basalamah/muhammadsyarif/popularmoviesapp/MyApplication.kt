package com.basalamah.muhammadsyarif.popularmoviesapp

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Muhammad Syarif on 1/31/2018.
 */
class MyApplication : Application() {
    companion object {
        lateinit var api:API
    }
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api=retrofit.create(API::class.java)
    }
}