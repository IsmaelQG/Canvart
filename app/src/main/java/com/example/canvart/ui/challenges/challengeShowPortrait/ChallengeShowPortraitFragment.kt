package com.example.canvart.ui.challenges.challengeShowPortrait

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
import com.example.canvart.databinding.FragmentChallengeShowPortraitBinding
import com.example.canvart.ui.challenges.challengeShowImage.*
import com.example.canvart.ui.challenges.imageChallengeRedo.ImageChallengeRedoFragment
import com.example.canvart.ui.challenges.portraitChallengeRedo.PortraitChallengeRedoFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ID_CHALLENGE = "ID_CHALLENGE"

class ChallengeShowPortraitFragment : Fragment(R.layout.fragment_challenge_show_portrait) {

    private val binding by viewBinding { FragmentChallengeShowPortraitBinding.bind(it)}

    private val viewModel : ChallengeShowPortraitViewModel by viewModels{
        ChallengeShowPortraitViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao,
                AppDatabase.getInstance(requireContext()).componentHeadDao,
                requireArguments().getLong(ID_CHALLENGE, 0)
        )
    }

    private val listAdapter: ChallengeShowAdapter = ChallengeShowAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        binding.lifecycleOwner = viewLifecycleOwner
        setupViews()
        setupRecyclerView()

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
        })
        viewModel.challenge.observe(viewLifecycleOwner, Observer {
            result ->
            binding.lblChallengeTitleDone.text = result.title
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
            }
        })
        viewModel.listParts.observe(viewLifecycleOwner, Observer {
            println(it)
            binding.lblDrawingDescShow.text = viewModel.concatenate(it)
        })
    }

    private fun listeners(){
        binding.flbRepeatChallenge.setOnClickListener {
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
            replace(R.id.fcDetail, PortraitChallengeRedoFragment.newInstance(requireArguments().getLong(ID_CHALLENGE, 0)))
            addToBackStack("")
        }
    }

    companion object{

        fun newInstance(idChallenge : Long) : ChallengeShowPortraitFragment =
                ChallengeShowPortraitFragment().apply {
                    arguments = bundleOf(ID_CHALLENGE to idChallenge)
                }

    }
}