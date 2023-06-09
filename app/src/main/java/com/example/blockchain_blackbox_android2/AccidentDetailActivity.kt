package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentDetailBinding

class AccidentDetailActivity: AppCompatActivity(){
    private lateinit var binding: ActivityAccidentDetailBinding
    var myVideoList: ArrayList<Video> = ArrayList<Video>()
    var nearCarVideoList: ArrayList<Video> = ArrayList<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentDetailBinding.inflate(layoutInflater)
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

        val myCarVideoLinearLayoutManager = LinearLayoutManager(this)
        myCarVideoLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvMyCarVideo.layoutManager = myCarVideoLinearLayoutManager
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

        binding.tvBtnOk.setOnClickListener{
            Toast.makeText(applicationContext, "처리 완료가 되었습니다", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}