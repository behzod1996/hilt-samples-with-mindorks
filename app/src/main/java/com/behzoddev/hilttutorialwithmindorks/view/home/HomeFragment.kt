package com.behzoddev.hilttutorialwithmindorks.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.behzoddev.hilttutorialwithmindorks.databinding.FragmentDashboardBinding
import com.behzoddev.hilttutorialwithmindorks.view.base.BaseFragment

class HomeFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}