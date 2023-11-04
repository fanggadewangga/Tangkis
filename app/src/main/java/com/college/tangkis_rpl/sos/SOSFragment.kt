package com.college.tangkis_rpl.sos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.college.tangkis_rpl.R
import com.college.tangkis_rpl.databinding.FragmentHomeBinding
import com.college.tangkis_rpl.databinding.FragmentSosBinding

class SOSFragment : Fragment() {

    private lateinit var binding: FragmentSosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSosBinding.inflate(layoutInflater)
        return binding.root
    }

}