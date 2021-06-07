package com.example.canvart.ui.adventure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.ItemAlreadyMadeChallengeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdventureAdapter(private val challengeDao: ChallengeDao) : ListAdapter<Challenge, AdventureAdapter.ViewHolder>(AdventureDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlreadyMadeChallengeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdventureAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(private val binding: ItemAlreadyMadeChallengeBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(challenge: Challenge){
            binding.challenge = challenge
            GlobalScope.launch {
                val drawings = challengeDao.queryAllDrawingsByChallengeIdNotLiveData(challenge.id)
                binding.drawings = drawings.size
            }
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

typealias OnItemClickListener = (position: Int) -> Unit