package com.example.canvart.ui.challenges

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.R
import com.example.canvart.base.observeEvent
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentChallengesBinding
import com.example.canvart.ui.preferences.SettingsFragment
import com.example.canvart.ui.tutorial.TutorialDialogFragment
import com.example.canvart.ui.tutorial.TutorialFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.canvart.utils.viewBinding

private const val TUTORIAL_DIALOG_TAG = "TUTORIAL_DIALOG_TAG"

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val binding by viewBinding { FragmentChallengesBinding.bind(it)}

    private val viewModel : ChallengesViewModel by activityViewModels(){
        ChallengesViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    private val tutorialDialogViewModel: TutorialDialogFragment.ViewModel by activityViewModels()

    private val listAdapter: ChallengesAdapter by lazy {
        ChallengesAdapter(
                AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        setupToolbar()
        setupFirstLog()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.VISIBLE
        listeners()
        observeDialogResponses()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView(){
        binding.lstChallenges.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            val itemTouchHelper = ItemTouchHelper(
                    object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
                        override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                        ): Boolean = false

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            viewModel.deleteChallenge(listAdapter.currentList[viewHolder.absoluteAdapterPosition])
                        }

                    }
            )
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.menu_list_challenges)
            setNavigationIcon(R.drawable.ic_info_dark)
            setOnMenuItemClickListener { onMenuItemClick(it) }
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

    private fun observeViewModel(){
        viewModel.challenges.observe(viewLifecycleOwner, Observer {
            result -> showChallenges(result)
        })
    }

    private fun showChallenges(challenges : List<Challenge>){
        listAdapter.submitList(challenges)
        binding.lblTest.visibility =if(challenges.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun listeners(){
        binding.flbGoToChallenges.setOnClickListener {
            viewModel.addChallenge()
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
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fcDetail, TutorialFragment.newInstance())
                addToBackStack("")
            }
        }
    }

    private fun onMenuItemClick(item : MenuItem) : Boolean{
        when(item.itemId){
            R.id.mnuFilter -> viewModel.changeFilterVisibility()
            R.id.mnuOptions -> navigateToSettings()
            else -> return false
        }
        return true
    }

    private fun navigateToSettings(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcDetail, SettingsFragment())
            addToBackStack("")
        }
    }

    companion object{

        fun newInstance() : ChallengesFragment =
            ChallengesFragment()

    }

}