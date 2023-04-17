package com.example.meetrip2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.meetrip2.R
import com.example.meetrip2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.commuTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_communityFragment)

        }

        binding.recomTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_recommendFragment)
        }

        binding.userTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_homeFragment_to_userFragment)
        }

        return binding.root
    }

}