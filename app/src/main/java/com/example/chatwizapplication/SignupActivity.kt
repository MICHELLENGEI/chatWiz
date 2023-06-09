package com.example.chatwizapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatwizapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignup2.setOnClickListener {
            val name = binding.etNameSignUp.text.toString()
            val email = binding.etEmailSignUp.text.toString()
            val phone = binding.etPhoneNumber.text.toString()
            val password = binding.etPasswordSignUp.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()


            if (checkFields()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()

                    if (it.isSuccessful) {

                        val user: FirebaseUser? = auth.currentUser
                        val userId: String = user!!.uid

                        databaseReference =
                            FirebaseDatabase.getInstance().getReference("users").child(userId)

                        val hashMap: HashMap<String, String> = HashMap()
                        hashMap.put("userId", userId)
                        hashMap.put("username", name)
                        hashMap.put("phoneNumber", phone)
                        hashMap.put("confirmPassword", confirmPassword)

                        databaseReference.setValue(hashMap).addOnCompleteListener() { dbTask ->

                            if (dbTask.isSuccessful) {
                                val intent3 = Intent(this@SignupActivity, HomeActivity::class.java)
                                startActivity(intent3)
                                finish()
                            } else {
                                Log.e("error", dbTask.exception.toString())
                            }
                        }
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
        val name = binding.etNameSignUp.text.toString()
        val emailS = binding.etEmailSignUp.text.toString()
        val phone = binding.etPhoneNumber.text.toString()
        val passwordS = binding.etPasswordSignUp.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()
        val regex = Regex("^(07|01)\\d{8}$")
        val pattern = Regex("^\\w+@gmail\\.com$")



        if (name == "") {
            Toast.makeText(this, "Name is a required field", Toast.LENGTH_SHORT).show()
            return false
        } else if (emailS == "") {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
            return false
        } else if (!pattern.matches(emailS)) {
            Toast.makeText(this, "Check the Email format", Toast.LENGTH_SHORT).show()
            return false
        } else if (phone == "") {
            Toast.makeText(this, "Phone Number is required", Toast.LENGTH_SHORT).show()
            return false
        } else if (!regex.matches(phone)) {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
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