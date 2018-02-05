package com.basalamah.muhammadsyarif.popularmoviesapp

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_reviews.view.*

/**
 * Created by Muhammad Syarif on 2/4/2018.
 */
class ReviewAdapter(private val listReview:List<ListReviews>): RecyclerView.Adapter<ReviewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.item_reviews,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }


    inner class Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(position: Int){
            val context = itemView.context
            val listResult:ListReviews = listReview[position]
            itemView.tvAuthor.text=listResult.reviewAuthor
            itemView.tvContent.text=listResult.reviewContent
            itemView.tvContent.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(listResult.reviewUrl))
                context.startActivity(intent)
            }
        }
    }
}