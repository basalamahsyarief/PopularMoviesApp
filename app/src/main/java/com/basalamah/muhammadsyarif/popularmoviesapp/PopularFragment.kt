package com.basalamah.muhammadsyarif.popularmoviesapp


import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log.e
import android.util.Log.i
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
class PopularFragment : Fragment() {

    private val movieList= mutableListOf<ListResults>()
    private lateinit var adapter:MovieAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView:View?=inflater?.inflate(R.layout.fragment_movies, container, false)
        getData()
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }
    private fun setupView(){
        adapter = MovieAdapter(movieList)
        rvMovie.layoutManager=GridLayoutManager(context,2)
        rvMovie.adapter = adapter
    }
    private fun getData(){
        if (isNetworkConnected()){
            return
        }else{
            AlertDialog.Builder(activity)
                    .setTitle("No Internet Connection")
                    .setMessage("It looks like your internet connection is off. Please turn it on and try again")
                    .setPositiveButton(android.R.string.ok,DialogInterface.OnClickListener { dialogInterface, i ->  })
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
        MyApplication.api.getPopular().enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                e(tag, t?.message)
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                i(tag,"data: ${Gson().toJsonTree(response?.code())}")
                val nMovieList=response?.body()?.results
                nMovieList?.let {
                    movieList.addAll(it)
                    adapter.notifyDataSetChanged()
                }

            }

        })
    }

    private fun isNetworkConnected():Boolean{
        val connecManager:ConnectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netwInfo:NetworkInfo=connecManager.activeNetworkInfo
        return netwInfo != null && netwInfo.isConnected
    }
}// Required empty public constructor
