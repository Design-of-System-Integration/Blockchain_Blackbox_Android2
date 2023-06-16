package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.blockchain_blackbox_android2.databinding.ActivityAccidentDetailBinding
import com.example.blockchain_blackbox_android2.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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

        binding.clAccidentHelp.setOnClickListener{
            val intent = Intent(applicationContext, AccidentHelpActivity::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId) {
            R.id.menu_item1 -> {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item2 -> {
                val intent = Intent(applicationContext, AlarmActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item3 -> {
                val intent = Intent(applicationContext, AlarmActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}