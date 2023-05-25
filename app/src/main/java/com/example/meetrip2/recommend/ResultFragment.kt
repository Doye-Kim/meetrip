package com.example.meetrip2.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import com.example.meetrip2.R
import com.example.meetrip2.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding : FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_result, container, false)


        setFragmentResultListener("requestKey1") { requestKey1, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result1 = bundle.getString("bundleKey1")
            // Do something with the result
            binding.re1.setText(result1)
        }

        setFragmentResultListener("requestKey2") { requestKey2, bundle ->
            val result2 = bundle.getString("bundleKey2")
            binding.re2.setText(result2)
        }

        setFragmentResultListener("requestKey3") { requestKey3, bundle ->
            val result3 = bundle.getString("bundleKey3")
            binding.re3.setText(result3)
        }

        setFragmentResultListener("requestKey4") { requestKey4, bundle ->
            val result4 = bundle.getString("bundleKey4")
            binding.re4.setText(result4)
        }

        return binding.root
    }
}