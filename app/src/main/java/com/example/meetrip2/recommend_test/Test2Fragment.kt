package com.example.meetrip2.recommend_test

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
import com.example.meetrip2.databinding.FragmentTest2Binding

class Test2Fragment : Fragment() {

    private lateinit var binding : FragmentTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test2, container, false)

        binding.mukBtn.setOnClickListener{

            val result = "muk"
            setFragmentResult("requestKey2", bundleOf("bundleKey2" to result))

            it.findNavController().navigate(R.id.action_test2Fragment_to_test3Fragment)

        }

        binding.placeBtn.setOnClickListener{

            val result = "place"
            setFragmentResult("requestKey2", bundleOf("bundleKey2" to result))

            it.findNavController().navigate(R.id.action_test2Fragment_to_test3Fragment)

        }

        return binding.root
    }

}