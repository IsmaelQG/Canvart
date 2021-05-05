package com.example.canvart.ui.challenges

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.canvart.R
import com.example.canvart.base.observeEvent
import com.example.canvart.databinding.FragmentChallengesBinding
import com.example.canvart.ui.tutorial.TutorialDialogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.iessaladillo.pedrojoya.tasks_app.utils.viewBinding

private const val TUTORIAL_DIALOG_TAG = "TUTORIAL_DIALOG_TAG"

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val binding by viewBinding { FragmentChallengesBinding.bind(it)}

    private val tutorialDialogViewModel: TutorialDialogFragment.ViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupFirstLog()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.VISIBLE
        listeners()
        observeDialogResponses()
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
        println("Valor: " + (sharedPreferences.getInt("userFirstLog", 2) == 0))
        if(sharedPreferences.getInt("userFirstLog", 2) == 0){
            showTutorialDialog()
            binding.lblTest.text = "El usuario se conectó por primera vez"
            with(sharedPreferences.edit()){
                putInt("userFirstLog", 1)
                apply()
            }
        }
        else if(sharedPreferences.getInt("userFirstLog", 2) == 1){
            binding.lblTest.text = "Funciona, el usuario ya se conectó de nuevo"
        }
    }

    private fun listeners(){
        binding.flbGoToChallenges.setOnClickListener {
            goToChallenges()
        }
    }

    private fun goToChallenges(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcDetail, ChallengesMenuFragment.newInstance())
            addToBackStack("")
        }
    }

    private fun showTutorialDialog() {
        TutorialDialogFragment()
            .show(parentFragmentManager, TUTORIAL_DIALOG_TAG)
    }

    private fun observeDialogResponses() {
        tutorialDialogViewModel.response.observeEvent(viewLifecycleOwner) {
            tutorialResponse(it)
        }
    }

    private fun tutorialResponse(response : Boolean){
        if(response){

        }
    }

    companion object{

        fun newInstance() : ChallengesFragment =
            ChallengesFragment()

    }

}