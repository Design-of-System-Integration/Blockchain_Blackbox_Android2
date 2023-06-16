package com.example.blockchain_blackbox_android2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blockchain_blackbox_android2.databinding.ItemVideoBinding

class MyCarVideoRVAdapter(private var accidentList: ArrayList<*>) :
    RecyclerView.Adapter<MyCarVideoRVAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var thumbnail: ImageView
        var date: TextView
        fun onBind(video: Video) {
//          thumbnail.setImageResource(item.getResourceId());
            date.text = video.date
        }

        init {
            thumbnail = binding.ivVideoThumbnail
            date = binding.tvVideoTime
            binding.root.setOnClickListener(View.OnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener!!.onItemClick(position)
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemVideoBinding =
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // holder.onBind(videoList.get(position));
    }

    fun setVideoList(list: ArrayList<*>) {
        accidentList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return accidentList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }
}