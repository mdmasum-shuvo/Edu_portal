package com.masum.edu_portal.feature.study.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemVerticalVideoLectureBinding
import com.masum.edu_portal.feature.study.data.all_study.Datum

class VideoLectureVerticalAdapter constructor(private val context: Context, private val list: List<Datum>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var listener: ItemClickListener

    init {
        layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val dashboardBinding: ItemVerticalVideoLectureBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_vertical_video_lecture, parent, false
        )
        return ViewFilesHolder(
            dashboardBinding
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewFilesHolder) {

            holder.bindView(context,list!!.get(position))
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    class ViewFilesHolder(itemBinding: ItemVerticalVideoLectureBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemVerticalVideoLectureBinding

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