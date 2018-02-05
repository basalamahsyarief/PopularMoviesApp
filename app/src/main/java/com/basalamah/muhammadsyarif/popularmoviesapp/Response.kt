package com.basalamah.muhammadsyarif.popularmoviesapp

import com.google.gson.annotations.SerializedName

/**
 * Created by Muhammad Syarif on 1/31/2018.
 */
data class MovieResponse(
        @field:SerializedName("results")
        val results:List<ListResults>?=null
)
data class TrailersResponse(
        @field:SerializedName("results")
        val results:List<ListTrailers>?=null
)
data class ReviewsResponse(
        @field:SerializedName("results")
        val results:List<ListReviews>?=null
)
data class ListResults(
        @field:SerializedName("vote_count")
        val voteCount:String?="",
        @field:SerializedName("id")
        val id:String?="",
        @field:SerializedName("vote_average")
        val voteAvg:String?="",
        @field:SerializedName("title")
        val title:String?="",
        @field:SerializedName("poster_path")
        val posterPath:String?="",
        @field:SerializedName("overview")
        val overview:String?="",
        @field:SerializedName("release_date")
        val relDate:String?="",
        @field:SerializedName("backdrop_path")
        val backdropPath:String?=""
)

data class ListTrailers(
        @field:SerializedName("key")
        val keyTrailers:String?=""
)

data class ListReviews(
        @field:SerializedName("author")
        val reviewAuthor:String?="",
        @field:SerializedName("content")
        val reviewContent:String?="",
        @field:SerializedName("url")
        val reviewUrl:String?=""
)