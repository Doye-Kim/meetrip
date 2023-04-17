package com.example.meetrip2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.meetrip2.R
import com.example.meetrip2.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private lateinit var binding : FragmentCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)

        binding.homeTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_communityFragment_to_homeFragment)
        }

        binding.recomTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_communityFragment_to_recommendFragment)
        }

        binding.userTap.setOnClickListener{

            it.findNavController().navigate(R.id.action_communityFragment_to_userFragment)
        }

        return binding.root
    }

}