package com.storyapp.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.storyapp.adapter.ListStoryAdapter
import com.storyapp.adapter.LoadingStateAdapter
import com.storyapp.databinding.FragmentHomeBinding
import com.storyapp.db.local.entity.Story
import com.storyapp.viewmodel.DetailStoryViewModel
import com.storyapp.viewmodel.SettingModelFactory
import com.storyapp.viewmodel.SettingPreferences
import com.storyapp.viewmodel.SettingViewModel
import com.storyapp.viewmodel.ViewModelFactory
import com.storyapp.viewmodel.dataStore

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var storyViewModel: DetailStoryViewModel? = null
    private var token = ""

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get token from preferences
        storyViewModel = ViewModelProvider(this, ViewModelFactory(activity as MainActivity))[DetailStoryViewModel::class.java]
        val pref = SettingPreferences.getInstance((activity as MainActivity).dataStore)
        val settingViewModel = ViewModelProvider(this, SettingModelFactory(pref))[SettingViewModel::class.java]

        settingViewModel.getUserTokens().observe(viewLifecycleOwner) {
            token = StringBuilder("Bearer ").append(it).toString()
            setListUser(it)
        }
        showLoading()
    }

    //set recycler view
    @OptIn(ExperimentalPagingApi::class)
    private fun setListUser(token: String) {
        val rvListStory = ListStoryAdapter()

        binding.rvStory.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = rvListStory.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    rvListStory.retry()
                }
            )
        }
        rvListStory.setOnItemClickCallback(object : ListStoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Story) {
                sendSelectedUser(data)
            }
        })
        storyViewModel?.getPagingStory(token)?.observe(viewLifecycleOwner) {
            rvListStory.submitData(lifecycle, it)
        }
    }

    private fun sendSelectedUser(data: Story) {
        val intent = Intent(context, DetailStoryActivity::class.java)
        intent.putExtra(DetailStoryActivity.EXTRA_DATA, data)
        startActivity(intent)
    }

    private fun showLoading() {
        storyViewModel?.isLoading?.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}