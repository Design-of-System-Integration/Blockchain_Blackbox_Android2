package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentDetailBinding
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentHistoryDetailBinding

class AccidentHistoryDetailActivity: AppCompatActivity(){
    private lateinit var binding: ActivityAccidentHistoryDetailBinding
    var myVideoList: ArrayList<Video> = ArrayList<Video>()
    var nearCarVideoList: ArrayList<Video> = ArrayList<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        myVideoList.add(Video("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4".toUri(), "hello1", ""))
        myVideoList.add(Video("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4".toUri(), "hello1", ""))

        nearCarVideoList.add(Video("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4".toUri(), "hello1", ""))
        nearCarVideoList.add(Video("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4".toUri(), "hello1", ""))
        nearCarVideoList.add(Video("https://rlpstorage.blob.core.windows.net/container0/accident1.mp4".toUri(), "hello1", ""))

        val myCarVideoRVAdapter = MyCarVideoRVAdapter(myVideoList)
        myCarVideoRVAdapter.setOnItemClickListener(object : MyCarVideoRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, VideoPlayActivity::class.java)
                startActivity(intent)
            }
        })

        val myVideoLinearLayoutManager = LinearLayoutManager(this)
        myVideoLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvMyCarVideo.layoutManager = myVideoLinearLayoutManager
        binding.rvMyCarVideo.adapter = myCarVideoRVAdapter

        val nearCarVideoRVAdapter = NearCarVideoRVAdapter(nearCarVideoList)
        nearCarVideoRVAdapter.setOnItemClickListener(object : NearCarVideoRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, VideoPlayActivity::class.java)
                startActivity(intent)
            }
        })

        val nearCarVideoLinearLayoutManager = LinearLayoutManager(this)
        nearCarVideoLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvNearCarVideo.layoutManager = nearCarVideoLinearLayoutManager
        binding.rvNearCarVideo.adapter = nearCarVideoRVAdapter
    }
}