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
import com.example.meetrip2.databinding.FragmentTest4Binding

class Test4Fragment : Fragment() {

    private lateinit var binding : FragmentTest4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_test4, container, false)

        binding.amBtn.setOnClickListener{

            val result = "am"
            setFragmentResult("requestKey4", bundleOf("bundleKey4" to result))

            it.findNavController().navigate(R.id.action_test4Fragment_to_resultFragment)

        }

        binding.pmBtn.setOnClickListener{

            val result = "pm"
            setFragmentResult("requestKey4", bundleOf("bundleKey4" to result))

            it.findNavController().navigate(R.id.action_test4Fragment_to_resultFragment)

        }

        return binding.root
    }

}