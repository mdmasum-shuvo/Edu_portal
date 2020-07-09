package com.masum.edu_portal.feature.homework.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemHomeworkBinding
import com.masum.edu_portal.databinding.SeeAllViewBinding
import com.masum.edu_portal.feature.homework.data.all_homework.Datum

class AllHomeWorkAdapter  constructor(val context: Context?, val list: List<Datum>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SEE_ALL_VIEW_TYPE = 0
    private val MEMBER_ITEM_VIEW_TYPE = 1
    private lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == SEE_ALL_VIEW_TYPE) {
            val seeAllViewBinding: SeeAllViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.see_all_view,
                parent,
                false
            )
            return SeeAllViewHolder(seeAllViewBinding)
        }
        val memberBinding: ItemHomeworkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_homework,
            parent,
            false
        )
        return MemberListViewHolder(memberBinding)

    }

    override fun getItemCount(): Int {
        return list!!.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            list!!.size -> {
                if (holder is SeeAllViewHolder) {
                    holder.bindView()
                    holder.binding.llSeeAllHolder.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(p0: View?) {
                            listener.onClick(position, holder.binding.llSeeAllHolder)
                        }
                    })
                }
            }
            else -> {
                if (holder is MemberListViewHolder) {

                    if (context != null) {
                        holder.bindView(context, list.get(position))
                    }
                    holder.binding.itemMember.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(p0: View?) {
                            listener.onClick(position, holder.binding.itemMember)

                        }
                    })
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list!!.size) return SEE_ALL_VIEW_TYPE
        return MEMBER_ITEM_VIEW_TYPE
    }

    internal class SeeAllViewHolder(itemBinding: SeeAllViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: SeeAllViewBinding

        init {
            binding = itemBinding
        }

        fun bindView() {
            binding.llSeeAllHolder.gravity = Gravity.CENTER
            binding.tvTextSeeAll.setText(itemView.context.resources.getString(R.string.see_all_homework))
        }
    }

    internal class MemberListViewHolder(itemBinding: ItemHomeworkBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemHomeworkBinding

        init {
            binding = itemBinding
        }

        fun bindView(context: Context, member: Datum) {
            binding.data = member
            /*        Glide.with(context)
                        .load(member.)
                        .placeholder(R.drawable.ic_person)
                        .into(binding.memberImage)*/



        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}