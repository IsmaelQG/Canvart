package com.example.canvart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.example.canvart.ui.challenges.ChallengesFragment
import com.example.canvart.databinding.MainActivityBinding
import com.example.canvart.ui.adventure.AdventureFragment
import com.example.canvart.ui.tips.TipsFragment

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
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            navigateToOption(it)
        }
    }

    private fun navigateToOption(item : MenuItem) : Boolean{
        when(item.itemId){
            R.id.mnuChallenges -> goToChallenges()
            R.id.mnuAdventure -> goToAdventure()
            R.id.mnuTips -> goToTips()
            else -> return false
        }
        return true
    }

    private fun goToChallenges(){
        supportFragmentManager.commit {
            replace(R.id.fcDetail, ChallengesFragment.newInstance())
        }
    }

    private fun goToAdventure(){
        supportFragmentManager.commit {
            replace(R.id.fcDetail, AdventureFragment.newInstance())
        }
    }

    private fun goToTips(){
        supportFragmentManager.commit {
            replace(R.id.fcDetail, TipsFragment.newInstance())
        }
    }
}