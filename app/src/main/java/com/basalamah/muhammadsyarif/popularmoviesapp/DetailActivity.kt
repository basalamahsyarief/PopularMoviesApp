package com.basalamah.muhammadsyarif.popularmoviesapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.i
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    var id:String?=""
    var title:String?=""
    var overview:String?=""
    var poster:String?=""
    var voteAvg:String?=""
    var voteCount:String?=""
    var releaseDate:String?=""
    var backDropPath:String?=""
    private val tag = this::class.java.simpleName
    private lateinit var adapter:TrailersAdapter
    private lateinit var reviewAdapter:ReviewAdapter
    private var trailerList = mutableListOf<ListTrailers>()
    private var reviewList = mutableListOf<ListReviews>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getExtraIntent()
        setupView()
        getTrailers()
        getReviews()
    }

    private fun getExtraIntent(){
        id = intent.getStringExtra("DATA_ID")
        title=intent.getStringExtra("DATA_TITLE")
        overview=intent.getStringExtra("DATA_OVERVIEW")
        poster=intent.getStringExtra("DATA_POSTER")
        voteAvg=intent.getStringExtra("DATA_VOTE_AVG")
        voteCount=intent.getStringExtra("DATA_COUNT")
        releaseDate=intent.getStringExtra("DATA_RELEASE_DATE")
        backDropPath=intent.getStringExtra("DATA_BACKDROP")

    }
    private fun setupView(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setExpandedTitleColor(Color.TRANSPARENT)
        tvTitle.text=title
        tvOverview.text=overview
        tvRate.text=voteAvg
        tvRelDate.text=releaseDate
        Glide.with(this).load("https://image.tmdb.org/t/p/w185${backDropPath}").error(R.mipmap.ic_launcher).into(ivBackDrop)
        Glide.with(this).load("https://image.tmdb.org/t/p/w185${poster}").error(R.mipmap.ic_launcher).into(ivPoster)
        adapter = TrailersAdapter(trailerList)
        rvTrailers.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvTrailers.adapter=adapter
        reviewAdapter = ReviewAdapter(reviewList)
        rvReviews.layoutManager=LinearLayoutManager(this)
        rvReviews.adapter=reviewAdapter
    }
    private fun getTrailers(){
        MyApplication.api.getTrailers(id).enqueue(object : Callback<TrailersResponse>{
            override fun onFailure(call: Call<TrailersResponse>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<TrailersResponse>?, response: Response<TrailersResponse>?) {
                i(tag,"data : ${Gson().toJsonTree(response?.body())}")
                response?.body()?.results?.let {
                    trailerList.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }
    private fun getReviews(){
        MyApplication.api.getReviews(id).enqueue(object :Callback<ReviewsResponse>{
            override fun onFailure(call: Call<ReviewsResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ReviewsResponse>?, response: Response<ReviewsResponse>?) {
                i(tag,"reviews: ${Gson().toJsonTree(response?.body())}")
                response?.body()?.results?.let {
                    reviewList.addAll(it)
                    reviewAdapter.notifyDataSetChanged()
                }
            }

        })
    }
}
