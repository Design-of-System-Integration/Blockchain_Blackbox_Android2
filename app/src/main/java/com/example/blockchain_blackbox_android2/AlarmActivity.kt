package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blockchain_blackbox_android2.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAlarmSpecify.setOnClickListener{
            val intent = Intent(applicationContext, VideoPlayActivity::class.java)
            startActivity(intent)
        }
    }
}