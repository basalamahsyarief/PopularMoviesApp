package com.basalamah.muhammadsyarif.popularmoviesapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment() {

    private val movieList= mutableListOf<ListResults>()
    private lateinit var adapter:MovieAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView:View = inflater!!.inflate(R.layout.fragment_movies, container, false)
        getData()
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }
    private fun setupView(){
        adapter = MovieAdapter(movieList)
        rvMovie.layoutManager= GridLayoutManager(context,2)
        rvMovie.adapter = adapter
    }
    private fun getData(){
        MyApplication.api.getTopRated().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.e(tag, t?.message)
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                Log.i(tag, "data: ${Gson().toJsonTree(response?.body())}")
                val nMovieList=response?.body()?.results
                nMovieList?.let {
                    movieList.addAll(it)
                    adapter.notifyDataSetChanged()
                }

            }

        })
    }
}// Required empty public constructor
