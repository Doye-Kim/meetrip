package com.example.meetrip2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.meetrip2.databinding.FragmentRecommendBinding
import com.example.meetrip2.reccomend.Signgu
import com.example.meetrip2.recommend_test.RecommendContentActivity
import com.google.firebase.database.DatabaseReference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

var tt = ""
class RecommendFragment : Fragment() {
    private lateinit var binding : FragmentRecommendBinding
    lateinit var myRef : DatabaseReference
    var arrList_items: ArrayList<Signgu> = ArrayList()
    var size = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false)

        binding.testBtn.setOnClickListener{
            val intent = Intent(context, RecommendContentActivity::class.java)
            startActivity(intent)
        }
        val client = OkHttpClient()
        val appkey = "appkey=e8wHh2tya84M88aReEpXCa5XTQf3xgo01aZG39k5"
        val type = "&type=sig"

//        val request = Request.Builder()
//            .url("https://apis.openapi.sk.com/puzzle/travel?"+appkey+type)
//            .get()
//            .addHeader("accept", "application/json")
//            .build()
//
//        val response = client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Toast.makeText(context, "불러오는 데에 실패했습니다.", Toast.LENGTH_SHORT).show()
//            }
//            override fun onResponse(call: Call, response: Response) {
//                Thread{
//                    var str = response.body?.string()
//                    val jsonobj: JSONObject = JSONObject(str)
//                    Log.d("obj", jsonobj.toString())
//
//                    var json_array: JSONArray = jsonobj.getJSONArray("contents")
//                    size = json_array.length()
//
//                    for (i in 0.. size-1){
//                        var json_objdetail: JSONObject = json_array.getJSONObject(i)
//                        var codes: Signgu = Signgu(
//                            json_objdetail.getString("districtCode"),
//                            json_objdetail.getString("districtName"),
//                        )
//                        arrList_items.add(codes!!)
//                    }
//                }.start()
//            }
//        })
        val request1 = Request.Builder()
            .url("https://apis.openapi.sk.com/puzzle/traveler-count/raw/monthly/districts/5011000000?"+appkey)
            .get()
            .addHeader("accept", "application/json")
            .build()

        val response1 = client.newCall(request1).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, "불러오는 데에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call, response: Response) {
                Thread{
                    var str = response.body?.string()
                    val jsonobj: JSONObject = JSONObject(str)
                    Log.d("obj", jsonobj.toString())

//                    var json_array: JSONArray = jsonobj.getJSONArray("contents")
//                    size = json_array.length()
//
//                    for (i in 0.. size-1){
//                        var json_objdetail: JSONObject = json_array.getJSONObject(i)
//                        var codes: Signgu = Signgu(
//                            json_objdetail.getString("districtCode"),
//                            json_objdetail.getString("districtName"),
//                        )
//                    }
                }.start()
            }
        })

        return binding.root
    }


/*
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
=======
        val database = Firebase.database
        myRef = database.getReference("ex")
//        val key = "jlfu7ZHfaDOrClksoEtP9SYsAgKhF%2FUWNkhnhHkgi3YiK2eY30WQKZSZxBz0v8Jo7KcMTrccAbK41XlHUzXh2w%3D%3D"
//        // 현재 페이지번호
//        val startYmd = "&startYmd=20230101"
//        val endYmd = "&endYmd=20230101"
//        val pageNo = "&pageNo=1"
//        // 한 페이지 결과 수
//        val numOfRows ="&numOfRows=10"
//        // AND(안드로이드)
//        val MobileOS = "&MobileOS=AND"
//        // 서비스명 = 어플명
//        val MobileApp = "&MobileApp=meetrip"
//        // API 정보를 가지고 있는 주소
//        val url = "https://apis.data.go.kr/B551011/DataLabService/locgoRegnVisitrDDList?serviceKey="+key+numOfRows+pageNo+MobileOS+MobileApp+startYmd+endYmd
//        Log.e("url", url)
>>>>>>> 9691d0f5d88b6a9fc395350ec363c64d8d507a27
        try {
//            val thread = Thread(NetworkThread(url))
//            thread.start()
//            thread.join()
//            binding.tv.setText(tt)
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
*/
//    class NetworkThread( var url: String): Runnable{
//        override fun run() {
//            try{
//                val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)
//
//                xml.documentElement.normalize()
//
//                val list: NodeList = xml.getElementsByTagName("item")
//
//                for(i in 0..list.length-1){
//                    val n: Node = list.item(i)
//                    if(n.getNodeType() == Node.ELEMENT_NODE){
//                        val elem = n as Element
//                        val map = mutableMapOf<String, String>()
//
//                        for(j in 0..elem.attributes.length - 1){
//                            map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
//                        }
//                        tt += "1. 위치: ${elem.getElementsByTagName("signguNm").item(0).textContent}\n"
//                        tt += "2. 관광객 구분: ${elem.getElementsByTagName("touDivNm").item(0).textContent}\n"
//                        tt += "3. 관광객 수: ${elem.getElementsByTagName("touNum").item(0).textContent}\n"
//
//                    }
//                }
//            }catch(e: Exception){
//                Log.e("OPEN API", e.toString())
//            }
//        }
//    }
}