package com.example.canvart.ui.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentTutorialBinding
import com.example.canvart.ui.adventure.AdventureAdapter
import com.example.canvart.ui.adventure.AdventureViewModel
import com.example.canvart.ui.adventure.AdventureViewModelFactory
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TutorialFragment : Fragment(R.layout.fragment_tutorial) {

    private val binding by viewBinding { FragmentTutorialBinding.bind(it) }

    private val viewModel : TutorialViewModel by viewModels {
        TutorialViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    private val listAdapter: AdventureAdapter by lazy {
        AdventureAdapter(
                AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupRecyclerView()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.tutorial_title)
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun setupRecyclerView(){
        binding.lstTutorials.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeViewModel(){
        viewModel.challenges.observe(viewLifecycleOwner, Observer {
            result -> showChallenges(result)
        })
    }

    private fun showChallenges(challenges : List<Challenge>){
        listAdapter.submitList(challenges)
    }

    private fun goBack(): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return true
    }

    companion object{

        fun newInstance() : TutorialFragment =
            TutorialFragment()

    }

}