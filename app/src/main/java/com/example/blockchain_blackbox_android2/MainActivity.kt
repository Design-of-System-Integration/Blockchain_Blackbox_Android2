package com.example.blockchain_blackbox_android2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentDetailBinding
import com.example.blockchain_blackbox_android2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clAccidentReceipt.setOnClickListener{
            val intent = Intent(applicationContext, AccidentRequestActivity::class.java)
            startActivity(intent)
        }

        binding.clAccidentHistory.setOnClickListener{
            val intent = Intent(applicationContext, AccidentHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.clAccidentNow.setOnClickListener{
            val intent = Intent(applicationContext, AccidentDetailActivity::class.java)
            startActivity(intent)
        }
    }
}