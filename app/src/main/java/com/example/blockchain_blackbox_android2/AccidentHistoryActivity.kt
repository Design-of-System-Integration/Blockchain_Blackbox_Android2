package com.example.blockchain_blackbox_android2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentHistoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AccidentHistoryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAccidentHistoryBinding
    var accidentHistoryList: ArrayList<AccidentHistory> = ArrayList<AccidentHistory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccidentHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roleVPAdapter = RoleVPAdapter(
            supportFragmentManager,
            lifecycle
        )
        val viewPager2 = findViewById<ViewPager2>(R.id.vp_role)
        viewPager2.adapter = roleVPAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout_role)
        TabLayoutMediator(
            tabLayout, viewPager2
        ) { tab, position ->
            if (position == 0) tab.text = "사고 당사자" else if (position == 1) tab.text = "도와준 이력"
        }.attach()
    }
}