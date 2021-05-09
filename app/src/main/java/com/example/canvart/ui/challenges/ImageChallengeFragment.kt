package com.example.canvart.ui.challenges

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.canvart.R
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.example.canvart.utils.viewBinding

class ImageChallengeFragment : Fragment(R.layout.fragment_image_challenge) {

    private val binding by viewBinding { FragmentImageChallengeBinding.bind(it)}

    private val viewModel : ImageChallengeViewModel by activityViewModels(){
        ImageChallengeViewModelFactory(
                binding
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stopTimer()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE

            val url = "https://source.unsplash.com/random"
            Picasso.get().load(url).into(binding.imgUserChallenge)

        viewModel.startTimer()

        listeners()
        observers()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.app_name)
            setNavigationIcon(R.drawable.ic_arrow_back_light)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun goBack(): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return true
    }

    private fun listeners(){
        binding.btnLeave.setOnClickListener {
            goBack()
        }
    }

    private fun observers(){
        viewModel.timerMillis.observe(viewLifecycleOwner, Observer {
            result -> binding.lblTimer.text = result.toString()
        })
    }

    companion object{

        fun newInstance() : ImageChallengeFragment =
            ImageChallengeFragment()

    }

}