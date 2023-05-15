package com.example.chatwizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.chatwizapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(ChatsFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.calls -> replaceFragment(CallsFragment())
                R.id.chats -> replaceFragment(ChatsFragment())
                R.id.notification -> replaceFragment(NotificationsFragment())

                else -> {

                }

            }
            true

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }
}