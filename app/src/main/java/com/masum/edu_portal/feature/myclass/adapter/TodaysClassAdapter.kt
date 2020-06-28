package com.masum.edu_portal.feature.myclass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemExamBinding
import com.masum.edu_portal.databinding.ItemTodaysClassBinding
import com.masum.edu_portal.feature.myclass.data.Datum

class TodaysClassAdapter constructor(private val context: Context?, private val list: List<Datum>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var listener: ItemClickListener


    init {
        layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val dashboardBinding: ItemTodaysClassBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_todays_class, parent, false
        )
        return  ViewFilesHolder(
            dashboardBinding
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewFilesHolder) {
            holder.binding.data=list!!.get(position)
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


    class ViewFilesHolder(itemBinding: ItemTodaysClassBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemTodaysClassBinding

        init {
            binding = itemBinding
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}