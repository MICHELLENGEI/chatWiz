package com.example.chatwizapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatwizapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intentLogIn = Intent(this, LoginActivity::class.java)
            startActivity(intentLogIn)
        }
        binding.btnSignup.setOnClickListener {
            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
        }
    }
}