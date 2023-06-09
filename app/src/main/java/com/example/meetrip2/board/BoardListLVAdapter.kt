package com.example.meetrip2.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.meetrip2.R

class BoardListLVAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var cvView = convertView
        if(cvView == null){
            cvView = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)
        }
        val title = cvView!!.findViewById<TextView>(R.id.titleArea)
        val content = cvView!!.findViewById<TextView>(R.id.contentArea)
        content.text = boardList[position].content
        val time = cvView!!.findViewById<TextView>(R.id.timeArea)
        title!!.text = boardList[position].title
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time


        return cvView!!
    }

}