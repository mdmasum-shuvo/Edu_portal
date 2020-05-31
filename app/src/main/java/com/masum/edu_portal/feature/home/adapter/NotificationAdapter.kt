/*
 * *
 *  * Created by Md Masum Talukder on 5/8/20 10:57 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/8/20 10:57 PM
 *
 */

package com.masum.edu_portal.feature.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemNewsFeedBinding
import com.masum.edu_portal.feature.home.data.notifications.Notification

class NotificationAdapter constructor(val context: Context, val list: List<Notification>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listener: ItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val memberBinding: ItemNewsFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_news_feed,
            parent,
            false
        )
        return MemberListViewHolder(memberBinding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MemberListViewHolder) {
            holder.bindView(context, list.get(position))


            holder.binding.item.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    listener.onClick(position,holder.binding.item)

                }
            })
        }
    }

    internal class MemberListViewHolder(itemBinding: ItemNewsFeedBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemNewsFeedBinding

        init {
            binding = itemBinding
        }

        fun bindView(context: Context, data: Notification) {
            binding.data = data

        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}