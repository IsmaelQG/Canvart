package com.example.canvart.ui.challenges.challengeShowDescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer
import com.example.canvart.databinding.FragmentChallengeShowDescriptionBinding
import com.example.canvart.ui.challenges.challengeShowImage.ChallengeShowAdapter
import com.example.canvart.ui.challenges.descriptonChallengeRedo.DescriptionChallengeRedoFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ID_CHALLENGE = "ID_CHALLENGE"

class ChallengeShowDescriptionFragment : Fragment(R.layout.fragment_challenge_show_description) {
    private val binding by viewBinding { FragmentChallengeShowDescriptionBinding.bind(it)}

    private val viewModel : ChallengeShowDescriptionViewModel by viewModels{
        ChallengeShowDescriptionViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDrawingDao,
                AppDatabase.getInstance(requireContext()).componentCharacterDao,
                requireArguments().getLong(ID_CHALLENGE, 0),
                requireContext()
        )
    }

    private val listAdapter: ChallengeShowAdapter = ChallengeShowAdapter().apply {
        setOnItemClickListener{
            viewModel.imagePopup.initiatePopupWithGlide(currentList[it].image)
            viewModel.imagePopup.viewPopup()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupViews()
        setupRecyclerView()
        viewModel.configurePopup()
    }

    private fun setupViews(){
        setupToolbar()
        observeViewModel()
        listeners()
    }

    private fun setupRecyclerView(){
        binding.lstDrawings.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun observeViewModel(){
        viewModel.drawings.observe(viewLifecycleOwner, Observer {
            result -> showDrawings(result)

            viewModel.listParts.observe(viewLifecycleOwner, Observer {
                if(result.isEmpty()){
                    binding.lblDrawingDescShow.text = "? ? ?"
                }
                else{
                    binding.lblDrawingDescShow.text = viewModel.concatenate(it)
                }
            })
        })
        viewModel.challenge.observe(viewLifecycleOwner, Observer {
            result ->
            binding.toolbar.title = result.title
            when(result.difficulty){
                Difficulty.EASY -> {
                    binding.lblChallengeDifficultyDone.setBackgroundResource(R.drawable.rounded_border_easy)
                    binding.lblChallengeDifficultyDone.setText(R.string.text_easy)
                }
                Difficulty.MEDIUM -> {
                    binding.lblChallengeDifficultyDone.setBackgroundResource(R.drawable.rounded_border_medium)
                    binding.lblChallengeDifficultyDone.setText(R.string.text_medium)
                }
                Difficulty.HARD -> {
                    binding.lblChallengeDifficultyDone.setBackgroundResource(R.drawable.rounded_border_hard)
                    binding.lblChallengeDifficultyDone.setText(R.string.text_hard)
                }
                Difficulty.ADVENTURE -> {
                    binding.lblChallengeDifficultyDone.setBackgroundResource(R.drawable.rounded_border_adventure)
                    binding.lblChallengeDifficultyDone.setText(R.string.adventure_text)
                }
                Difficulty.TUTORIAL -> {
                    binding.lblChallengeDifficultyDone.setBackgroundResource(R.drawable.rounded_border_tutorial)
                    binding.lblChallengeDifficultyDone.setText(R.string.tutorial_text)
                }
            }
            when(result.material){
                Material.PENCIL -> {
                    binding.lblChallengeMaterialDone.setText(R.string.text_pencil)
                }
                Material.PEN -> {
                    binding.lblChallengeMaterialDone.setText(R.string.text_pen)
                }
                Material.MARKER -> {
                    binding.lblChallengeMaterialDone.setText(R.string.text_marker)
                }
                Material.ALL -> {
                    binding.lblChallengeMaterialDone.setText(R.string.text_all_material)
                }
            }
            when(result.timer){
                Timer.ONE_MIN -> {
                    binding.lblChallengeTimerDone.setText(R.string.oneMinute)
                }
                Timer.TWO_MIN -> {
                    binding.lblChallengeTimerDone.setText(R.string.twoMinutes)
                }
                Timer.FIVE_MIN -> {
                    binding.lblChallengeTimerDone.setText(R.string.fiveMinutes)
                }
                Timer.TEN_MIN -> {
                    binding.lblChallengeTimerDone.setText(R.string.tenMinutes)
                }
                Timer.THIRTY_MIN -> {
                    binding.lblChallengeTimerDone.setText(R.string.thirtyMinutes)
                }
                Timer.INFINITE -> {
                    binding.lblChallengeTimerDone.setText(R.string.infMinutes)
                }
            }
        })

    }

    private fun listeners(){
        binding.flbRepeatChallenge.setOnClickListener {
            println("Navegando a repetir")
            goToRepeat()
        }
    }

    private fun showDrawings(drawings : List<Drawing>){
        listAdapter.submitList(drawings)
    }

    private fun goBack(): Boolean {
        requireActivity().onBackPressed()
        return true
    }

    private fun goToRepeat(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, DescriptionChallengeRedoFragment.newInstance(requireArguments().getLong(ID_CHALLENGE, 0)))
            addToBackStack("")
        }
    }

    companion object{

        fun newInstance(idChallenge : Long) : ChallengeShowDescriptionFragment =
                ChallengeShowDescriptionFragment().apply {
                    arguments = bundleOf(ID_CHALLENGE to idChallenge)
                }

    }
}