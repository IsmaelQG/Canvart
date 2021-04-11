package com.example.canvart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.canvart.challenges.ChallengesFragment
import com.example.canvart.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private val binding : MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fcDetail, ChallengesFragment())
            }
        }
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {

    }
}