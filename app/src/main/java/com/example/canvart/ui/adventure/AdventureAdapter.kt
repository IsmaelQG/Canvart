package com.example.canvart.ui.adventure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.R
import com.example.canvart.data.dao.DrawingDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.databinding.ItemAlreadyMadeChallengeBinding
import com.example.canvart.databinding.ItemChallengeBinding
import com.example.canvart.ui.challenges.ChallengesAdapter

class AdventureAdapter(private val drawingDao: DrawingDao) : ListAdapter<Challenge, AdventureAdapter.ViewHolder>(AdventureDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlreadyMadeChallengeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdventureAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemAlreadyMadeChallengeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(challenge: Challenge){
            binding.challenge = challenge
        }
    }

    object AdventureDiffCallback : DiffUtil.ItemCallback<Challenge>(){
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