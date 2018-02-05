package com.basalamah.muhammadsyarif.popularmoviesapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_movies.view.*

/**
 * Created by Muhammad Syarif on 1/31/2018.
 */
class MovieAdapter(private val movieList:List<ListResults>): RecyclerView.Adapter<MovieAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.item_movies,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int {
       return movieList.size
    }


    inner class Holder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(position: Int){
            val context = itemView.context
            val movieResults:ListResults=movieList[position]
            i("adapter","movie: ${Gson().toJsonTree(movieResults)}")
            itemView.tvItem.text=movieResults.title
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w185${movieResults.posterPath}")
                    .error(R.mipmap.ic_launcher)
                    .into(itemView.ivItem)
            itemView.ivItem.setOnClickListener{
                val intent = Intent(context,DetailActivity::class.java)
                intent.putExtra("DATA_ID",movieResults.id)
                intent.putExtra("DATA_TITLE",movieResults.title)
                intent.putExtra("DATA_POSTER",movieResults.posterPath)
                intent.putExtra("DATA_BACKDROP",movieResults.backdropPath)
                intent.putExtra("DATA_OVERVIEW",movieResults.overview)
                intent.putExtra("DATA_VOTE_AVG",movieResults.voteAvg)
                intent.putExtra("DATA_VOTE_COUNT",movieResults.voteCount)
                intent.putExtra("DATA_RELEASE_DATE",movieResults.relDate)


                context.startActivity(intent)
            }
        }
    }
}