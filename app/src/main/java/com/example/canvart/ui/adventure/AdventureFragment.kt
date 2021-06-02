package com.example.canvart.ui.adventure

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentAdventureBinding
import com.example.canvart.ui.challenges.challengeShowDescription.ChallengeShowDescriptionFragment
import com.example.canvart.ui.challenges.challengeShowImage.ChallengeShowFragment
import com.example.canvart.ui.challenges.challengeShowPortrait.ChallengeShowPortraitFragment
import com.example.canvart.ui.tutorial.TutorialFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdventureFragment : Fragment(R.layout.fragment_adventure) {

    private val binding by viewBinding { FragmentAdventureBinding.bind(it) }

    private val viewModel : AdventureViewModel by viewModels {
        AdventureViewModelFactory(
            AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    private val listAdapter: AdventureAdapter by lazy {
        AdventureAdapter(
            AppDatabase.getInstance(requireContext()).challengeDao
        ).apply {
            setOnItemClickListener{
                println("Title: "+currentList[it].title)
                goToDrawingsImage(currentList[it])
            }
        }
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
            result ->
            viewModel.challengesWithDrawings.observe(viewLifecycleOwner, Observer {
                resultVm -> showChallenges(result.subList(0, resultVm.size+1))
            })
        })
    }

    private fun showChallenges(challenges : List<Challenge>){
        listAdapter.submitList(challenges)
        binding.lblTest.visibility =if(challenges.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun goToDrawingsImage(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowFragment.newInstance(challenge.id))
            addToBackStack("")
        }
    }

    private fun goToDrawingsPortrait(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowPortraitFragment.newInstance(challenge.id))
            addToBackStack("")
        }
    }

    private fun goToDrawingsDescription(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowDescriptionFragment.newInstance(challenge.id))
            addToBackStack("")
        }
    }

    companion object{

        fun newInstance() : AdventureFragment =
                AdventureFragment()

    }

}