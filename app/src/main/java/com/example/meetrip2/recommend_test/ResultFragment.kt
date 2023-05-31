package com.example.meetrip2.recommend_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import com.example.meetrip2.R
import com.example.meetrip2.RecommendFragment
import com.example.meetrip2.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding : FragmentResultBinding

    var result1 = " "
    var result2 = " "
    var result3 = " "
    var result4 = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_result, container, false)

        /*
        binding.testEndBtn.setOnClickListener{
            val intent = Intent(context, RecommendFragment::class.java)
            startActivity(intent)
        }*/


        setFragmentResultListener("requestKey1") { requestKey1, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            result1 = bundle.getString("bundleKey1").toString()
            //binding.re1.setText(result1)
            Log.d("ddddd",result1)
            // Do something with the result
        }

        setFragmentResultListener("requestKey2") { requestKey2, bundle ->
            result2 = bundle.getString("bundleKey2").toString()
            //binding.re2.setText(result2)
        }

        setFragmentResultListener("requestKey3") { requestKey3, bundle ->
            result3 = bundle.getString("bundleKey3").toString()
            //binding.re3.setText(result3)
        }

        setFragmentResultListener("requestKey4") { requestKey4, bundle ->
            result4 = bundle.getString("bundleKey4").toString()
            //binding.re4.setText(result4)

            //Log.d("ddddd",result1)
            //Log.d("ddddd",result2)
            //Log.d("ddddd",result3)
            //Log.d("ddddd",result4)

            if(result1=="san"&&result2=="muk"&&result3=="heal"&&result4=="am"){
                Log.d("ddddd","san,muk,heal,am")
                binding.resultImage.setImageResource(R.drawable.test_image_1)
                binding.resultPlace.setText("소확행 힐링카페\n 순창 베르자당도")
            }
            else if(result1=="san"&&result2=="muk"&&result3=="heal"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_2)
                binding.resultPlace.setText("신비로운 숲속길\n 부산 이바구길")
            }
            else if(result1=="san"&&result2=="muk"&&result3=="hot"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_3)
                binding.resultPlace.setText("풍경 맛집\n 남해 금산산장")
            }
            else if(result1=="san"&&result2=="muk"&&result3=="hot"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_4)
                binding.resultPlace.setText("청춘이 반짝\n 부산 호천마을")
            }
            else if(result1=="san"&&result2=="place"&&result3=="heal"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_5)
                binding.resultPlace.setText("삼재없는 천년고찰\n 해남 두륜산 대흥사")
            }
            else if(result1=="san"&&result2=="place"&&result3=="heal"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_6)
                binding.resultPlace.setText("하늘에서 하는 산책\n 천마산 하늘전망대")
            }
            else if(result1=="san"&&result2=="place"&&result3=="hot"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_7)
                binding.resultPlace.setText("부담없는 산책코스\n 부천 원미공원")
            }
            else if(result1=="san"&&result2=="place"&&result3=="hot"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_8)
                binding.resultPlace.setText("별바다 시티뷰\n 부산 황령산 전망대")
            }
            else if(result1=="sea"&&result2=="muk"&&result3=="heal"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_9)
                binding.resultPlace.setText("바다를 담은 떡볶이\n 제주 방긋스낵")
            }
            else if(result1=="sea"&&result2=="muk"&&result3=="heal"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_10)
                binding.resultPlace.setText("낭만적인 시간\n 영종도 카페아라")
            }
            else if(result1=="sea"&&result2=="muk"&&result3=="hot"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_11)
                binding.resultPlace.setText("신선한 갈치구이\n 제주 만민식당")
            }
            else if(result1=="sea"&&result2=="muk"&&result3=="hot"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_12)
                binding.resultPlace.setText("조개구이 천국\n 영종도 을왕리해변")
            }
            else if(result1=="sea"&&result2=="place"&&result3=="heal"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_13)
                binding.resultPlace.setText("고래가 뛰노는\n 영덕 고래불해수욕장")
            }
            else if(result1=="sea"||result2=="place"||result3=="heal"||result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_14)
                binding.resultPlace.setText("무지갯빛 밤바다\n 통영 통영대교")
            }
            else if(result1=="sea"&&result2=="place"&&result3=="hot"&&result4=="am"){
                binding.resultImage.setImageResource(R.drawable.test_image_15)
                binding.resultPlace.setText("바다 가장 가까이\n 울진 죽변해안스카이레일")
            }
            else if(result1=="sea"&&result2=="place"&&result3=="hot"&&result4=="pm"){
                binding.resultImage.setImageResource(R.drawable.test_image_16)
                binding.resultPlace.setText("동양의 나폴리\n 부산 송도해수욕장")
            }
        }

        //binding.resultImage.setImageResource(R.drawable.test_image_1)
        //binding.resultPlace.setText("소확행 힐링카페\n 순창 베르자당도")

        return binding.root
    }
}