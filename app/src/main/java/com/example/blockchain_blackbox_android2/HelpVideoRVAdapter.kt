package com.example.blockchain_blackbox_android2

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blockchain_blackbox_android2.databinding.ItemVideoBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HelpVideoRVAdapter(private var videoList: ArrayList<*>, private val activity: AccidentHelpActivity) :
    RecyclerView.Adapter<HelpVideoRVAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var thumbnail: ImageView
        var date: TextView
        fun onBind(video: Video) {
            thumbnail.setImageBitmap(createThumbnail(activity, video.uri.toString() ))
            //  date.text = extractDate(activity, video.uri.toString()).toString()
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
        holder.onBind(videoList.get(position) as Video)
    }

    fun setVideoList(list: ArrayList<*>) {
        videoList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    fun createThumbnail(activity: Context, path: String): Bitmap? {
        var mediaMetadataRetriever: MediaMetadataRetriever? = null
        var bitmap: Bitmap? = null
        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(activity, Uri.parse(path))
            bitmap = mediaMetadataRetriever.getFrameAtTime(1000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaMetadataRetriever?.release()
        }
        return bitmap
    }

    fun extractDate(activity: Context, path: String): Date?{
        var dateStr: String? = null
        var dateFormat: Date? = null
        var mediaMetadataRetriever: MediaMetadataRetriever? = null

        try {
            mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(activity, Uri.parse(path))

            var dateStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)
            if (dateStr != null) {
                val formatter = SimpleDateFormat("yyyyMMdd'T'HHmmss.SSS'Z'", Locale.getDefault())
                dateFormat = formatter.parse(dateStr)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaMetadataRetriever?.release()
        }
        return dateFormat
    }
}
