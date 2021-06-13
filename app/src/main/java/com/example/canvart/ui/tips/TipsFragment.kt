package com.example.canvart.ui.tips

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.entity.Tip
import com.example.canvart.databinding.FragmentTipsBinding
import com.example.canvart.ui.adventure.AdventureAdapter
import com.example.canvart.ui.tutorial.TutorialViewModel
import com.example.canvart.ui.tutorial.TutorialViewModelFactory
import com.example.canvart.utils.viewBinding
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

class TipsFragment : Fragment(R.layout.fragment_tips) {

    private val binding by viewBinding { FragmentTipsBinding.bind(it) }

    private val viewModel : TipsViewModel by viewModels {
        TipsViewModelFactory(
            AppDatabase.getInstance(requireContext()).tipDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE)
        )
    }

    private val listAdapter: TipsAdapter by lazy {
        TipsAdapter().apply {
            setOnItemClickListener{
                viewModel.changeTipVisibility(currentList[it].id)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        setupToolbar()
        setupRecyclerView()
        setupViews()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.tips_text)
        }
    }

    private fun setupRecyclerView(){
        binding.lstTips.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = ScaleInAnimationAdapter(AlphaInAnimationAdapter(listAdapter).apply {
                // Change the durations.
                setDuration(1000)
                // Change the interpolator.
                setInterpolator(OvershootInterpolator())
                // Disable the first scroll mode.
                setFirstOnly(false)
            })
        }
    }

    private fun setupViews(){
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.tips.observe(viewLifecycleOwner, Observer {
                result ->
            viewModel.levelLiveData.observe(viewLifecycleOwner, Observer {
                resultLevel ->
                showTips(result.filter { it.unlockLevel <= resultLevel})
            })
        })
    }

    private fun showTips(tips : List<Tip>){
        listAdapter.submitList(tips)
        if(tips.isNotEmpty()){
            binding.lblTextEmpty.visibility = View.GONE
            binding.lblTextEmptyTitle.visibility = View.GONE
            binding.imgLock.visibility = View.GONE
        }
        else{
            binding.lblTextEmpty.visibility = View.VISIBLE
            binding.lblTextEmptyTitle.visibility = View.VISIBLE
            binding.imgLock.visibility = View.VISIBLE
        }
    }

    override fun onDestroy(){
        viewModel.hideAllTips()
        super.onDestroy()
    }

    companion object{

        fun newInstance() : TipsFragment =
            TipsFragment()

    }

}