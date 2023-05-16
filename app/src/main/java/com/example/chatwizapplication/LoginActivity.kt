package com.example.chatwizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatwizapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin2.setOnClickListener {

            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            if (checkAllFields()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful) {
                        val intent1 = Intent(this@LoginActivity,HomeActivity::class.java)
                        startActivity(intent1)
                        finish()
                    } else {
                        Log.e("error", it.exception.toString())
                    }
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intentTvSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentTvSignup)
        }
    }

    private fun checkAllFields(): Boolean {
        val emailL = binding.etEmailLogin.text.toString()
        val passwordL = binding.etPasswordLogin.text.toString()
        if (emailL == "") {
            Toast.makeText(this, "Email is a required field", Toast.LENGTH_SHORT).show()
            return false
        } else if (passwordL == "") {
            Toast.makeText(this, "Password is a required field", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}