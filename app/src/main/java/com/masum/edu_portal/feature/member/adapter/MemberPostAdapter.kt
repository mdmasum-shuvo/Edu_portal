/*
 * *
 *  * Created by Md Masum Talukder on 5/7/20 6:16 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/7/20 6:16 AM
 *
 */

package com.masum.edu_portal.feature.member.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemMemberPostBinding
import com.masum.edu_portal.feature.home.data.posts.Datum

class MemberPostAdapter constructor(val context: Context, val list: List<Datum>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var listener: ItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val memberBinding: ItemMemberPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_member_post,
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


            /*        holder.binding.itemHolder.setOnClickListener(object :View.OnClickListener{
                        override fun onClick(p0: View?) {
                            listener.onClick(position,holder.binding.itemHolder)

                        }
                    })*/
        }
    }

    internal class MemberListViewHolder(itemBinding: ItemMemberPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemMemberPostBinding

        init {
            binding = itemBinding
        }

        fun bindView(context: Context, data: Datum) {
            binding.data = data
            Glide.with(context)
                .load(data.memberimage)
                .placeholder(R.drawable.cicle_img_p)
                .into(binding.img)

            if (data.image != null) {
                Glide.with(context)
                    .load( data.image)
                    .placeholder(R.drawable.ic_image_place)
                    .into(binding.imgStatus)
                binding.imgStatus.visibility = View.VISIBLE

            } else {
                binding.imgStatus.visibility = View.GONE
            }
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}
