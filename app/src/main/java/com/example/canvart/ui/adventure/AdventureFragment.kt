package com.example.canvart.ui.adventure

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
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentAdventureBinding
import com.example.canvart.ui.challenges.ChallengesAdapter
import com.example.canvart.ui.tutorial.TutorialFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdventureFragment : Fragment(R.layout.fragment_adventure) {

    private val binding by viewBinding { FragmentAdventureBinding.bind(it) }

    private val viewModel : AdventureViewModel by activityViewModels() {
        AdventureViewModelFactory(
            AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    private val listAdapter: AdventureAdapter by lazy {
        AdventureAdapter(
            AppDatabase.getInstance(requireContext()).drawingDao
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews()
        setupRecyclerView()
    }

    private fun setupToolbar(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.VISIBLE
        binding.toolbar.run {
            title = getString(R.string.adventure_text)
            inflateMenu(R.menu.menu_list_adventure)
            setNavigationIcon(R.drawable.ic_info_dark)
            setOnMenuItemClickListener { onMenuItemClick(it) }
        }
    }

    private fun setupViews(){
        observeViewModel()
    }

    private fun setupRecyclerView(){
        binding.lstAdventure.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun onMenuItemClick(item : MenuItem) : Boolean{
        when(item.itemId){
            R.id.mnuTutorial -> navigateToTutorial()
            else -> return false
        }
        return true
    }
    private fun navigateToTutorial(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcDetail, TutorialFragment.newInstance())
            addToBackStack("")
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

    companion object{

        fun newInstance() : AdventureFragment =
                AdventureFragment()

    }

}