package com.example.canvart.ui.challenges

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.canvart.R
import com.example.canvart.databinding.FragmentChallengesBinding
import es.iessaladillo.pedrojoya.tasks_app.utils.viewBinding

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val binding by viewBinding { FragmentChallengesBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupFirstLog()
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.menu_list_challenges)
            setNavigationIcon(R.drawable.ic_info_light)
        }
    }

    private fun setupFirstLog(){
        val sharedPreferences =
                activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPreferences.edit()){
            if(sharedPreferences.getInt("userFirstLog", 2) == 2){
                putInt("userFirstLog", 0)
                apply()
            }
        }
        println("Valor: " + (sharedPreferences?.getInt("userFirstLog", 2) == 0))
        if(sharedPreferences?.getInt("userFirstLog", 2) == 0){
            binding.lblTest.text = "El usuario se conectó por primera vez"
            with(sharedPreferences.edit()){
                putInt("userFirstLog", 1)
                apply()
            }
        }
        else if(sharedPreferences?.getInt("userFirstLog", 2) == 1){
            binding.lblTest.text = "Funciona, el usuario ya se conectó de nuevo"
        }
    }

    companion object{

        fun newInstance() : ChallengesFragment =
            ChallengesFragment()

    }

}