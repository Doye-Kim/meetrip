package com.example.meetrip2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.meetrip2.databinding.FragmentHomeBinding
import com.example.meetrip2.databinding.FragmentRecommendBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.lang.Exception
import javax.xml.parsers.DocumentBuilderFactory
var tt = ""
class RecommendFragment : Fragment() {
    private lateinit var binding : FragmentRecommendBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false)
        val key = "jlfu7ZHfaDOrClksoEtP9SYsAgKhF%2FUWNkhnhHkgi3YiK2eY30WQKZSZxBz0v8Jo7KcMTrccAbK41XlHUzXh2w%3D%3D"
        // 현재 페이지번호
        val startYmd = "&startYmd=20230101"
        val endYmd = "&endYmd=20230101"
        val pageNo = "&pageNo=1"
        // 한 페이지 결과 수
        val numOfRows ="&numOfRows=10"
        // AND(안드로이드)
        val MobileOS = "&MobileOS=AND"
        // 서비스명 = 어플명
        val MobileApp = "&MobileApp=meetrip"
        // API 정보를 가지고 있는 주소
        val url = "https://apis.data.go.kr/B551011/DataLabService/locgoRegnVisitrDDList?serviceKey="+key+numOfRows+pageNo+MobileOS+MobileApp+startYmd+endYmd
        Log.e("url", url)
        try {
//            Thread {
//                val client = OkHttpClient()
//                val response: Response
//
//                val request = Request.Builder()
//                    .url("https://apis.openapi.sk.com/puzzle/travel?type=sig")
//                    .get()
//                    .addHeader("accept", "application/json")
//                    .addHeader("appkey", "KaJbwjBlgh1ON8VMvULcl4HvD06jHwEe13oh4pFa")
//                    .build()
//
//                response = client.newCall(request).execute()
//                Log.d("!", response.toString())
//            }.start()
            val thread = Thread(NetworkThread(url))
            thread.start()
            thread.join()
            binding.tv.setText(tt)
        }
        catch(e: Exception){
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

        return binding.root
    }
    class NetworkThread( var url: String): Runnable{
        override fun run() {
            try{
                val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

                xml.documentElement.normalize()

                val list: NodeList = xml.getElementsByTagName("item")

                for(i in 0..list.length-1){
                    val n: Node = list.item(i)
                    if(n.getNodeType() == Node.ELEMENT_NODE){
                        val elem = n as Element
                        val map = mutableMapOf<String, String>()

                        for(j in 0..elem.attributes.length - 1){
                            map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                        }
                        tt += "1. 위치: ${elem.getElementsByTagName("signguNm").item(0).textContent}\n"
                        tt += "2. 관광객 구분: ${elem.getElementsByTagName("touDivNm").item(0).textContent}\n"
                        tt += "3. 관광객 수: ${elem.getElementsByTagName("touNum").item(0).textContent}\n"

                    }
                }
            }catch(e: Exception){
                Log.e("OPEN API", e.toString())
            }
        }
    }
}