package com.college.tangkis_rpl.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.tangkis_rpl.adapter.HomeArticleAdapter
import com.college.tangkis_rpl.adapter.HomeContactAdapter
import com.college.tangkis_rpl.contact.ContactActivity
import com.college.tangkis_rpl.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var contactAdapter: HomeContactAdapter
    private lateinit var articleAdapter: HomeArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setupView()
        getUserData()
        getContacts()
        getArticle()
        observe()
        return binding.root
    }

    private fun observe() {
        viewModel.articleLiveData.observe(this.requireActivity()) { articles ->
            articleAdapter.articles = articles
            articleAdapter.notifyDataSetChanged()
        }

        viewModel.contactLiveData.observe(this.requireActivity()) { contacts ->
            contactAdapter.contacts = contacts
            contactAdapter.notifyDataSetChanged()
        }

        viewModel.userLiveData.observe(requireActivity()) {
            binding.tvHeader.text = "Selamat Datang ${it.name}"
        }
    }

    private fun getArticle() {
        viewModel.getArticle()
    }

    private fun getContacts() {
        viewModel.getContacts()
    }

    private fun getUserData() {
        viewModel.getUserData()
    }

    private fun setupView() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this.requireActivity())[HomeViewModel::class.java]
        contactAdapter = HomeContactAdapter()
        articleAdapter = HomeArticleAdapter()

        binding.tvSeeAllContact.setOnClickListener {
            val intent = Intent(this.context, ContactActivity::class.java)
            startActivity(intent)
        }

        binding.rvArticle.apply {
            adapter = articleAdapter
            layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvContact.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}