package com.example.canvart.ui.challenges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.ItemChallengeBinding

class ChallengesAdapter(private val challengeDao: ChallengeDao) : ListAdapter<Challenge, ChallengesAdapter.ViewHolder>(ChallengeDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChallengeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemChallengeBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(challenge: Challenge){
                    binding.lblChallengeTitle.text = challenge.title
                }
            }

    object ChallengeDiffCallback :DiffUtil.ItemCallback<Challenge>(){
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.title == newItem.title&&
                    oldItem.state == newItem.state&&
                    oldItem.attempts == newItem.attempts&&
                    oldItem.difficulty == newItem.difficulty

    }

}