package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockchain_blackbox_android2.databinding.FragmentAccidentHistoryBinding

class AccidentHistoryFragment : Fragment() {
    private lateinit var binding: FragmentAccidentHistoryBinding
    var accidentHistoryList: ArrayList<AccidentHistory> = ArrayList<AccidentHistory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccidentHistoryBinding.inflate(layoutInflater)

        accidentHistoryList.add(AccidentHistory("", "2023-06-05", "잠실역 사고", "진행 중"))
        accidentHistoryList.add(AccidentHistory("", "2023-06-03", "삼성역 사고", "완료"))

        val accidentHistoryRVAdapter = AccidentHistoryRVAdapter(accidentHistoryList)
        accidentHistoryRVAdapter.setOnItemClickListener(object : AccidentHistoryRVAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(activity, AccidentHistoryDetailActivity::class.java)
                startActivity(intent)
            }
        })

        val accidentHistoryLinearLayoutManager = LinearLayoutManager(activity)
        accidentHistoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvAccidentHistory.layoutManager = accidentHistoryLinearLayoutManager
        binding.rvAccidentHistory.adapter = accidentHistoryRVAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // View 객체 초기화 및 이벤트 처리 등을 수행
    }
}