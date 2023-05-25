package com.example.meetrip2.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.meetrip2.R
import com.example.meetrip2.databinding.FragmentTest1Binding

class Test1Fragment : Fragment() {

    private lateinit var binding : FragmentTest1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test1, container, false)

        binding.sanBtn.setOnClickListener{

            val result = "san"
            setFragmentResult("requestKey1", bundleOf("bundleKey1" to result))

            it.findNavController().navigate(R.id.action_test1Fragment_to_test2Fragment)

        }

        binding.seaBtn.setOnClickListener{

            val result = "sea"
            setFragmentResult("requestKey1", bundleOf("bundleKey1" to result))

            it.findNavController().navigate(R.id.action_test1Fragment_to_test2Fragment)

        }

        return binding.root
    }
}