package com.example.canvart.ui.challenges.challengeShowImage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Drawing
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.databinding.FragmentChallengeShowBinding
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.example.canvart.ui.challenges.imageChallengeRedo.ImageChallengeRedoFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ID_CHALLENGE = "ID_CHALLENGE"

class ChallengeShowFragment : Fragment(R.layout.fragment_challenge_show) {

    private val binding by viewBinding { FragmentChallengeShowBinding.bind(it)}

    private val viewModel : ChallengeShowImageViewModel by viewModels{
        ChallengeShowImageViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao,
                AppDatabase.getInstance(requireContext()).imageURLDao,
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
            }
        })
        viewModel.imgUrl.observe(viewLifecycleOwner, Observer {
            result ->
            val circularProgressDrawable = CircularProgressDrawable(requireContext())
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(requireContext())
                .load(result)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(binding.imgChallengeImgReferenceDone)
        })
    }

    private fun listeners(){
        binding.flbRepeatChallenge.setOnClickListener {
            goToRepeat()
        }
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
            replace(R.id.fcDetail, ImageChallengeRedoFragment.newInstance(requireArguments().getLong(ID_CHALLENGE, 0)))
            addToBackStack("")
        }
    }

    private fun showDrawings(drawings : List<Drawing>){
        listAdapter.submitList(drawings)
    }

    private fun goBack(): Boolean {
        requireActivity().onBackPressed()
        return true
    }

    companion object{

        fun newInstance(idChallenge : Long) : ChallengeShowFragment =
                ChallengeShowFragment().apply {
                    arguments = bundleOf(ID_CHALLENGE to idChallenge)
                }

    }
}