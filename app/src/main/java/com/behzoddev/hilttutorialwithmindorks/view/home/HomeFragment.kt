package com.behzoddev.hilttutorialwithmindorks.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.behzoddev.hilttutorialwithmindorks.common.toastLong
import com.behzoddev.hilttutorialwithmindorks.common.toastShort
import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.databinding.FragmentDashboardBinding
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkResult
import com.behzoddev.hilttutorialwithmindorks.utils.Resource
import com.behzoddev.hilttutorialwithmindorks.view.adapter.HomeAdapter
import com.behzoddev.hilttutorialwithmindorks.view.base.BaseFragment
import com.behzoddev.hilttutorialwithmindorks.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentDashboardBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        lifecycleScope.launchWhenCreated {
            viewModel.users.collect { result ->
                when(result) {
                    is Resource.OnLoading -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.OnSuccess -> {
                        if(binding.rvList.adapter == null) {
                            binding.rvList.adapter = HomeAdapter()
                        }
                        (binding.rvList.adapter as HomeAdapter).differ.submitList(result.data)
                        binding.rvList.visibility = View.VISIBLE

                    }
                    is Resource.OnError -> result.throwable.message ?.let {
                        context?.toastLong(it)
                    }
                }
            }
        }
    }

    private fun setupUI() = with(binding)  {
        Log.d("Debug","setupUI function is stated")
        rvList.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter()
        rvList.addItemDecoration(
            DividerItemDecoration(
                rvList.context,
                (rvList.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvList.adapter = adapter
    }

    private fun setupObserver() {

    }
    private fun onUserLoaded(user: List<User>) = adapter.differ.submitList(user)
    private fun showUI(){
        binding.progressBar.visibility = View.GONE
        binding.rvList.visibility = View.VISIBLE
    }
}