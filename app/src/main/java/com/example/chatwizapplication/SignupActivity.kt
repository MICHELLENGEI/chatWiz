package com.example.chatwizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.chatwizapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.btnSignup2.setOnClickListener {
            val email = binding.etEmailSignUp.text.toString()
            val password = binding.etPasswordSignUp.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (checkFields()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()

                    if (it.isSuccessful) {
                        val intent3 = Intent(this, HomeActivity::class.java)
                        startActivity(intent3)
                    } else {
                        Log.e("error", it.exception.toString())
                    }
                }
            }
        }
        binding.tvLogin.setOnClickListener {
            val intentTvLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentTvLogin)
        }
    }

    private fun checkFields(): Boolean {
        val emailS = binding.etEmailSignUp.text.toString()
        val passwordS = binding.etPasswordSignUp.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()
        if (emailS == "") {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()) {
            Toast.makeText(this, "Check the Email format", Toast.LENGTH_SHORT).show()
            return false
        } else if (passwordS == "") {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            return false
        } else if (confirmPass == "") {
            Toast.makeText(this, "Confirm Password", Toast.LENGTH_SHORT).show()
            return false
        } else if (passwordS != confirmPass) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etPasswordSignUp.length() <= 8) {
            Toast.makeText(this, "Password is must be at least 8 characters", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true

    }
}