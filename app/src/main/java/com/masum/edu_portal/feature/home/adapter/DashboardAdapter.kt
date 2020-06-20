package com.masum.edu_portal.feature.home.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.edu_portal.R
import com.masum.edu_portal.common.callback_listener.ItemClickListener
import com.masum.edu_portal.databinding.ItemHomeDashboardBinding
import com.masum.edu_portal.databinding.SeeAllViewBinding
import com.masum.edu_portal.feature.home.data.Dashboard

class DashboardAdapter constructor(private val context: Context?, private val list: List<Dashboard>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    val REQUEST_UPDATE = 100
    private lateinit var listener: ItemClickListener


    init {
        layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

                val dashboardBinding: ItemHomeDashboardBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_dashboard, parent, false
                )
              return  ViewFilesHolder(
                    dashboardBinding
                )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

                if (holder is ViewFilesHolder) {
                    holder.binding.tvItemName.setText(list!!.get(position).string)
                    holder.binding.img.setImageDrawable(list!!.get(position).image)
                    holder.itemView.setOnClickListener {
                        listener.onClick(position, holder.binding.img)
                    }
                }
    }

    internal class SeeAllViewHolder(itemBinding: SeeAllViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView() {
            val linearLayout =
                itemView.findViewById<LinearLayout>(R.id.ll_see_all_holder)
            linearLayout.gravity = Gravity.TOP
            val image =
                itemView.findViewById<ImageView>(R.id.iv_image_see_all)
            image.setImageResource(R.drawable.ic_see_all_speciality)
            val textView = itemView.findViewById<TextView>(R.id.tv_text_see_all)
            textView.text = itemView
                .context
                .resources
                .getString(R.string.seeAll)
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    class ViewFilesHolder(itemBinding: ItemHomeDashboardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemHomeDashboardBinding

        init {
            binding = itemBinding
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}
