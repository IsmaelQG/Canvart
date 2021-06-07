package com.example.canvart.ui.challenges.challengesList

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.*
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
import com.example.canvart.ui.challenges.challengeShowDescription.ChallengeShowDescriptionFragment
import com.example.canvart.ui.challenges.challengeShowImage.ChallengeShowFragment
import com.example.canvart.ui.challenges.challengeShowPortrait.ChallengeShowPortraitFragment
import com.example.canvart.ui.challenges.challengesMenu.ChallengesMenuFragment
import com.example.canvart.ui.filters.ChallengeFilter
import com.example.canvart.ui.preferences.SettingsFragment
import com.example.canvart.ui.tutorial.TutorialDialogFragment
import com.example.canvart.ui.tutorial.TutorialFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.canvart.utils.viewBinding
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

private const val TUTORIAL_DIALOG_TAG = "TUTORIAL_DIALOG_TAG"

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val binding by viewBinding { FragmentChallengesBinding.bind(it)}

    private val viewModel : ChallengesViewModel by viewModels{
        ChallengesViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao
        )
    }

    private val tutorialDialogViewModel: TutorialDialogFragment.ViewModel by activityViewModels()

    private val listAdapter: ChallengesAdapter by lazy {
        ChallengesAdapter(
                AppDatabase.getInstance(requireContext()).challengeDao,
                requireActivity()
        ).apply {
            setOnItemClickListener{
                askType(currentList[it])
            }
        }
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
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = ScaleInAnimationAdapter(AlphaInAnimationAdapter(listAdapter).apply {
                // Change the durations.
                setDuration(1000)
                // Change the interpolator.
                setInterpolator(OvershootInterpolator())
                // Disable the first scroll mode.
                setFirstOnly(false)
            })
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
            with(sharedPreferences.edit()){
                putInt("userFirstLog", 1)
                apply()
            }
        }
        else if(sharedPreferences.getInt("userFirstLog", 2) == 1){

        }
    }

    private fun observeViewModel(){
        viewModel.challenges.observe(viewLifecycleOwner, Observer {
            result ->
            if(viewModel.condScrollUp == -1){
                viewModel.condScrollUp = result.size
            }
            showChallenges(result)
        })
    }

    private fun showChallenges(challenges : List<Challenge>){
        listAdapter.submitList(challenges)
        if(challenges.size > viewModel.condScrollUp){
            binding.lstChallenges.scrollToPosition(0)
        }
        viewModel.condScrollUp = challenges.size
        binding.lblTest.visibility =if(challenges.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun listeners(){
        binding.flbGoToChallenges.setOnClickListener {
            goToChallenges()
        }
        binding.rdgFilterDifficult.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                binding.rdbEasy.id -> viewModel.changeListByDifficulty(ChallengeFilter.EASY)
                binding.rdbMedium.id -> viewModel.changeListByDifficulty(ChallengeFilter.MEDIUM)
                binding.rdbHard.id -> viewModel.changeListByDifficulty(ChallengeFilter.HARD)
                binding.rdbAll.id -> viewModel.changeListByDifficulty(ChallengeFilter.ALL)
            }
        }
    }

    private fun goToChallenges(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
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
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        0,
                        R.anim.slide_out
                )
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
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, SettingsFragment())
            addToBackStack("")
        }
    }

    private fun askType(challenge: Challenge){
        viewModel.listIdChallengesImage.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsImage(challenge)
            }
        })
        viewModel.listIdChallengesPortrait.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsPortrait(challenge)
            }
        })
        viewModel.listIdChallengesDescription.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsDescription(challenge)
            }
        })
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

        fun newInstance() : ChallengesFragment =
            ChallengesFragment()

    }

}