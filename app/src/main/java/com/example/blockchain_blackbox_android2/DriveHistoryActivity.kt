package com.example.blockchain_blackbox_android2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blockchain_blackbox_android2.databinding.ActivityDriveHistoryBinding

class DriveHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDriveHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriveHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}