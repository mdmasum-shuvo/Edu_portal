package com.masum.edu_portal.feature.member.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemAttendanceBinding
import com.masum.edu_portal.feature.member.data.profile.attendance.Datum

class AttendanceAdapter constructor(private val context: Context?, private val list: List<Datum>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var listener: ItemClickListener


    init {
        layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val dashboardBinding: ItemAttendanceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_attendance, parent, false
        )
        return ViewFilesHolder(
            dashboardBinding
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewFilesHolder) {
            holder.binding.data = list!!.get(position)
            if (list[position].attendanceStatus.equals("P")) {
                holder.binding.imgStatus.setBackground(
                    context!!.resources.getDrawable(R.drawable.circle_shape_green)
                )
            } else {
                holder.binding.imgStatus.setBackground(
                    context!!.resources.getDrawable(R.drawable.circle_shape_red)
                )
            }
            holder.itemView.setOnClickListener {
            }
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    class ViewFilesHolder(itemBinding: ItemAttendanceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemAttendanceBinding

        init {
            binding = itemBinding
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}