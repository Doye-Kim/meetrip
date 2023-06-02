package com.example.meetrip2.reccomend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meetrip2.R

class RecommendRVAdapter(val context : Context,
                       val items: ArrayList<RecommendModel>)
    : RecyclerView.Adapter<RecommendRVAdapter.Viewholder>() {
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recommend_rv_item, parent, false)
        return Viewholder(v)
    }
    override fun onBindViewHolder(holder: Viewholder, position: Int){
        holder.bindItems(items[position])
        if(itemClick != null){
            holder.itemView.setOnClickListener{v ->
                itemClick?.onClick(v, position)
            }
        }
    }
    override fun getItemCount(): Int{
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item: RecommendModel){

            itemView.setOnClickListener{
                Toast.makeText(context, item.place, Toast.LENGTH_SHORT).show()
                //intent.putExtra("url", item.weburl)
                //itemView.context.startActivity(Intent(context, ContentShowActivity::class.java))
            }

            val placeTextArea = itemView.findViewById<TextView>(R.id.recTextArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.recImageArea)

            placeTextArea.text = item.place

            Glide.with(context)
                .load(item.photo)
                .into(imageViewArea)

        }
    }
}
