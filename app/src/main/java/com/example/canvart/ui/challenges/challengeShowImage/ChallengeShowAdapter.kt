package com.example.canvart.ui.challenges.challengeShowImage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.data.entity.Drawing
import com.example.canvart.databinding.ItemDrawingBinding

class ChallengeShowAdapter : ListAdapter<Drawing, ChallengeShowAdapter.ViewHolder>(DrawingDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDrawingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(private val binding: ItemDrawingBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(drawing: Drawing){
            binding.drawing = drawing
            binding.rtScore.rating = drawing.score.toFloat()

        }
    }

    object DrawingDiffCallback : DiffUtil.ItemCallback<Drawing>(){
        override fun areItemsTheSame(oldItem: Drawing, newItem: Drawing): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Drawing, newItem: Drawing): Boolean =
            oldItem.challengeId == newItem.challengeId&&
                    oldItem.timeFinish == newItem.timeFinish&&
                    oldItem.image == newItem.image&&
                    oldItem.score == newItem.score&&
                    oldItem.description == newItem.description

    }


}

typealias OnItemClickListener = (position: Int) -> Unit