package com.example.canvart.ui.challenges

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.canvart.R
import com.example.canvart.databinding.FragmentChallengeDoneBinding
import com.example.canvart.utils.viewBinding

class ChallengeDoneFragment : Fragment(R.layout.fragment_challenge_done) {

    private val binding by viewBinding { FragmentChallengeDoneBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

}