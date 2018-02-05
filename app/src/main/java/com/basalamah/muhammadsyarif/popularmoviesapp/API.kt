package com.basalamah.muhammadsyarif.popularmoviesapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Muhammad Syarif on 1/31/2018.
 */
interface API {
    @GET("popular?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getPopular(): Call<MovieResponse>
    @GET("top_rated?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getTopRated():Call<MovieResponse>
    @GET("latest?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getLatest():Call<MovieResponse>
    @GET("{id_movie}/videos?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getTrailers(@Path("id_movie") idMovie: String?):Call<TrailersResponse>
    @GET("{id_movie}/reviews?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getReviews(@Path("id_movie") idMovie: String?):Call<ReviewsResponse>

}