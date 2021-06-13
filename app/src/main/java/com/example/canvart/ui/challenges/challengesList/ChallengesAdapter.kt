package com.example.canvart.ui.challenges.challengesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.R
import com.example.canvart.data.dao.ChallengeDrawingDao
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.data.enums.Timer
import com.example.canvart.databinding.ItemChallengeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChallengesAdapter(private val challengeDrawingDao: ChallengeDrawingDao, private val activity : FragmentActivity) : ListAdapter<Challenge, ChallengesAdapter.ViewHolder>(ChallengeDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChallengeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(private val binding: ItemChallengeBinding):
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
                        withContext(Dispatchers.IO) {
                            val drawing = challengeDrawingDao.queryDrawingByChallengeId(challenge.id)
                            val drawings = challengeDrawingDao.queryAllDrawingsByChallengeIdNotLiveData(challenge.id)
                            binding.drawing = drawing
                            binding.rtScore.rating =  drawing.score.toFloat()
                            binding.drawings = drawings.size
                        }
                    }
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
                    when(challenge.material) {
                        Material.PEN -> binding.lblChallengeMaterial.setText(R.string.text_pen)
                        Material.PENCIL -> binding.lblChallengeMaterial.setText(R.string.text_pencil)
                        Material.MARKER -> binding.lblChallengeMaterial.setText(R.string.text_marker)
                        Material.ALL -> binding.lblChallengeMaterial.setText(R.string.text_all_material)
                    }
                    when(challenge.timer){
                        Timer.ONE_MIN -> binding.lblChallengeTimer.setText(R.string.oneMinute)
                        Timer.TWO_MIN -> binding.lblChallengeTimer.setText(R.string.twoMinutes)
                        Timer.FIVE_MIN -> binding.lblChallengeTimer.setText(R.string.fiveMinutes)
                        Timer.TEN_MIN -> binding.lblChallengeTimer.setText(R.string.tenMinutes)
                        Timer.THIRTY_MIN -> binding.lblChallengeTimer.setText(R.string.thirtyMinutes)
                        Timer.INFINITE -> binding.lblChallengeTimer.setText(R.string.infMinutes)
                    }
                }
            }

    object ChallengeDiffCallback :DiffUtil.ItemCallback<Challenge>(){
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean =
            oldItem.title == newItem.title&&
                    oldItem.difficulty == newItem.difficulty&&
                    oldItem.material == newItem.material&&
                    oldItem.type == newItem.type&&
                    oldItem.index == newItem.index

    }

}

typealias OnItemClickListener = (position: Int) -> Unit