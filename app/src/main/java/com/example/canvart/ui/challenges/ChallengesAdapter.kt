package com.example.canvart.ui.challenges

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.R
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
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
                    binding.challenge = challenge
                    when(challenge.difficulty){
                        Difficulty.EASY ->{
                            binding.lblChallengeDifficulty.setBackgroundResource(R.drawable.rounded_border_easy)
                            binding.lblChallengeDifficulty.setText(R.string.text_easy)
                        }
                        Difficulty.MEDIUM -> {
                            binding.lblChallengeDifficulty.setBackgroundResource(R.drawable.rounded_border_medium)
                            binding.lblChallengeDifficulty.setText(R.string.text_medium)
                        }
                        Difficulty.HARD -> {
                            binding.lblChallengeDifficulty.setBackgroundResource(R.drawable.rounded_border_hard)
                            binding.lblChallengeDifficulty.setText(R.string.text_hard)
                        }
                    }
                    when(challenge.material){
                        Material.PEN -> binding.lblChallengeMaterial.setText(R.string.text_pen)
                        Material.PENCIL -> binding.lblChallengeMaterial.setText(R.string.text_pencil)
                        Material.MARKER -> binding.lblChallengeMaterial.setText(R.string.text_marker)
                    }
                }
            }

    object ChallengeDiffCallback :DiffUtil.ItemCallback<Challenge>(){
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.title == newItem.title&&
                    oldItem.state == newItem.state&&
                    oldItem.attempts == newItem.attempts&&
                    oldItem.difficulty == newItem.difficulty&&
                    oldItem.material == newItem.material&&
                    oldItem.type == newItem.type&&
                    oldItem.index == newItem.index

    }

}