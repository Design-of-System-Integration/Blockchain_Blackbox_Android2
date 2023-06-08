package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentRequestBinding

class AccidentRequestActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAccidentRequestBinding
    var myVideoList: ArrayList<Video> = ArrayList<Video>()
    var nearCarVideoList: ArrayList<Video> = ArrayList<Video>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMyDriveHistory.setOnClickListener{
            val intent = Intent(applicationContext, DriveHistoryActivity::class.java)
            startActivity(intent)
        }

        myVideoList.add(Video("title1", "hello1", ""))
        myVideoList.add(Video("title1", "hello1", ""))

        nearCarVideoList.add(Video("title1", "hello1", ""))
        nearCarVideoList.add(Video("title1", "hello1", ""))
        nearCarVideoList.add(Video("title1", "hello1", ""))

        val myVideoRVAdapter = MyVideoRVAdapter(myVideoList)
        myVideoRVAdapter.setOnItemClickListener(object : MyVideoRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, VideoPlayActivity::class.java)
                startActivity(intent)
            }
        })

        val myVideoLinearLayoutManager = LinearLayoutManager(this)
        myVideoLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvMyVideo.layoutManager = myVideoLinearLayoutManager
        binding.rvMyVideo.adapter = myVideoRVAdapter
    }
}