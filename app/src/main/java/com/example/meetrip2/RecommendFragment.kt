package com.example.meetrip2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class RecommendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            Thread {
                val client = OkHttpClient()
                val response: Response

                val request = Request.Builder()
                    .url("https://apis.openapi.sk.com/puzzle/travel?type=sig")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("appkey", "KaJbwjBlgh1ON8VMvULcl4HvD06jHwEe13oh4pFa")
                    .build()

                response = client.newCall(request).execute()
                Log.d("!", response.toString())
            }.start()
        }
        catch(e: java.lang.Exception){
            Log.e("error", e.toString())
        }


//        val request1 = Request.Builder()
//            .url("https://apis.openapi.sk.com/puzzle/traveler-count/raw/monthly/districts/5011000000?yearMonth=latest&gender=all&ageGrp=all&companionType=all")
//            .get()
//            .addHeader("accept", "application/json")
//            .addHeader("appkey", "gyWzs7T8nb4ln6FPVAFG73xlZawQ8iX8dC5u04Cd")
//            .build()
//
//        val response1 = client.newCall(request1).execute()

        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }
}