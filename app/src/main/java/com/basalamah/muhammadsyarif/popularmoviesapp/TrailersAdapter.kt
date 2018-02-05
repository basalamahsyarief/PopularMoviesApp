package com.basalamah.muhammadsyarif.popularmoviesapp

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_trailers.view.*


/**
 * Created by Muhammad Syarif on 2/2/2018.
 */
class TrailersAdapter(private val trailersList: List<ListTrailers>): RecyclerView.Adapter<TrailersAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int {
       return trailersList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.item_trailers,parent,false)
        return Holder(view)
    }

    inner class Holder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){
            val context = itemView.context
            val trailerResult:ListTrailers=trailersList[position]
            Glide.with(context).load("http://img.youtube.com/vi/${ trailerResult.keyTrailers}/hqdefault.jpg")
                    .error(R.mipmap.ic_launcher).into(itemView.ivTrailer)
            itemView.ivTrailer.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=${trailerResult.keyTrailers}"))
                context.startActivity(intent)
            }
        }
    }
}