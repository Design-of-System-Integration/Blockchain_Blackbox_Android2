package com.example.blockchain_blackbox_android2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blockchain_blackbox_android2.databinding.ActivityAlarmBinding
import com.example.blockchain_blackbox_android2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text .toString()

            if(id == "10000" && password == "1234") {
                Toast.makeText(this, "로그인 되었습니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            else if(id == null){
                Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(password == null){
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "회원정보가 올바르지 않습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}