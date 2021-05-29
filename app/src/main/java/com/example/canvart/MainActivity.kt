package com.example.canvart
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.canvart.databinding.MainActivityBinding
import com.example.canvart.ui.adventure.AdventureFragment
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment.Companion.REQUEST_CODE_PERMISSIONS
import com.example.canvart.ui.challenges.challengesList.ChallengesFragment
import com.example.canvart.ui.tips.TipsFragment
import java.io.File
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {

    private val binding : MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Canvart)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fcDetail, ChallengesFragment())
            }
        }

        setupBottomNavigationView()
        setupViews()
    }

    private fun setupViews() {
        val sharedPreferences =
            getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreferences.edit()){
            if(sharedPreferences.getInt("userFirstLog", 2) == 2){
                putInt("userFirstLog", 0)
                apply()
            }
            if(sharedPreferences.getInt("userLevel", -1) == -1){
                putInt("userLevel", 0)
                apply()
            }
            if(sharedPreferences.getInt("difficulty", -1) == -1){
                putInt("difficulty", 0)
                apply()
            }
            if(sharedPreferences.getInt("material", -1) == -1){
                putInt("material", 0)
                apply()
            }
            if(sharedPreferences.getInt("timer", -1) == -1){
                putInt("timer", 3)
                apply()
            }
        }
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

   override fun onBackPressed() {
       println("vjfudjnvu   " + supportFragmentManager.findFragmentById(R.id.fcDetail))
       println("fhusdhvud  $ChallengesFragment")
       if (supportFragmentManager.findFragmentById(R.id.fcDetail) is ChallengesFragment || supportFragmentManager.findFragmentById(R.id.fcDetail) is AdventureFragment || supportFragmentManager.findFragmentById(R.id.fcDetail) is TipsFragment) {
           super.onBackPressed()
       } else {
           supportFragmentManager.popBackStack(
                   null,
                   FragmentManager.POP_BACK_STACK_INCLUSIVE
           )
       }

   }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults:
            IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}