package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentHistoryBinding

class AccidentHistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAccidentHistoryBinding
    var accidentHistoryList: ArrayList<AccidentHistory> = ArrayList<AccidentHistory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        accidentHistoryList.add(AccidentHistory("", "2023-06-05", "잠실역 사고", "진행 중"))
        accidentHistoryList.add(AccidentHistory("", "2023-06-03", "삼성역 사고", "완료"))

        val accidentHistoryRVAdapter = AccidentHistoryRVAdapter(accidentHistoryList)
        accidentHistoryRVAdapter.setOnItemClickListener(object : AccidentHistoryRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, AccidentDetailActivity::class.java)
                startActivity(intent)
            }
        })

        val accidentHistoryLinearLayoutManager = LinearLayoutManager(this)
        accidentHistoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvAccidentHistory.layoutManager = accidentHistoryLinearLayoutManager
        binding.rvAccidentHistory.adapter = accidentHistoryRVAdapter
    }
}