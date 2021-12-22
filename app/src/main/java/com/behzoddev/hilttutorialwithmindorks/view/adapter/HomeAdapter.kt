package com.behzoddev.hilttutorialwithmindorks.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behzoddev.hilttutorialwithmindorks.common.loadUrlWithGlide
import com.behzoddev.hilttutorialwithmindorks.common.loadUrlWithPicasso
import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.databinding.ItemLayoutBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val differCallBack = object  : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                Log.d("Debug","areItemsTheSame created")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                Log.d("Debug","areContentsTheSame created")
                return oldItem == newItem
            }

        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        Log.d("Debug","onCreateViewHolder is created")
        return HomeViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("Debug","onBindViewHolder is created")
        val user = differ.currentList[position]
        holder.binding.ivUser.loadUrlWithPicasso(user.avatar)
        holder.binding.tvUserName.text = user.name
        holder.binding.tvDescription.text = user.email
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}