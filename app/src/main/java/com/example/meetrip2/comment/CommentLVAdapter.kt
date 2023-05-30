package com.example.meetrip2.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.meetrip2.R

class CommentLVAdapter(val commentList : MutableList<CommentModel>) : BaseAdapter() {
    override fun getCount(): Int{
        return commentList.size
    }

    override fun getItem(position: Int): Any {
        return commentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var cvView = convertView
        if(cvView == null){
            cvView = LayoutInflater.from(parent?.context).inflate(R.layout.comment_list_item, parent,false)
        }

        val comment = cvView!!.findViewById<TextView>(R.id.commentArea)
        comment.text = commentList[position].comment
        val time = cvView!!.findViewById<TextView>(R.id.timeArea)
        time!!.text = commentList[position].time

        return cvView!!
    }
}