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
import com.example.meetrip2.databinding.FragmentTest3Binding

class Test3Fragment : Fragment() {

    private lateinit var binding : FragmentTest3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test3, container, false)

        binding.healBtn.setOnClickListener{

            val result = "heal"
            setFragmentResult("requestKey3", bundleOf("bundleKey3" to result))

            it.findNavController().navigate(R.id.action_test3Fragment_to_test4Fragment)

        }

        binding.hotBtn.setOnClickListener{

            val result = "hot"
            setFragmentResult("requestKey3", bundleOf("bundleKey3" to result))

            it.findNavController().navigate(R.id.action_test3Fragment_to_test4Fragment)

        }

        return binding.root
    }

}