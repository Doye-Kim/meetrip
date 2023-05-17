package com.example.meetrip2.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meetrip2.R
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef

class ContentsRVAdapter(val context : Context,
                        val items: ArrayList<ContentsModel>,
                        val keyList: ArrayList<String>,
                        val bookmarkIdList: MutableList<String>)
    : RecyclerView.Adapter<ContentsRVAdapter.Viewholder>() {

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contents_rv_item, parent, false)
        return Viewholder(v)
    }
    override fun onBindViewHolder(holder: Viewholder, position: Int){
        holder.bindItems(items[position], keyList[position])
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
        fun bindItems(item : ContentsModel, key : String){

            itemView.setOnClickListener{
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                //intent.putExtra("url", item.weburl)
                //itemView.context.startActivity(Intent(context, ContentShowActivity::class.java))
            }

            val contentsTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookmarkIdList.contains(key)){
                bookmarkArea.setImageResource(R.drawable.bookmarkfill)
            }
            else {
                bookmarkArea.setImageResource(R.drawable.bookmark3)
            }
            bookmarkArea.setOnClickListener{
                Log.d("ContentsRVAdapter", FBAuth.getUid())
                Toast.makeText(context, key, Toast.LENGTH_LONG).show()
                if(bookmarkIdList.contains(key)){ //이미 북마크 되어 있을 때
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue()
                }
                else{ // 북마크 안 되어 있을 때
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true));
                }
            }
            contentsTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }
}