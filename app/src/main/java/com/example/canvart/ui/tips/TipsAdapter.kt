package com.example.canvart.ui.tips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.canvart.data.entity.Tip
import com.example.canvart.databinding.ItemTipBinding

class TipsAdapter : ListAdapter<Tip, TipsAdapter.ViewHolder>(TipsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTipBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipsAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(private val binding: ItemTipBinding):
        RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(tip : Tip){
            binding.tip = tip
            binding.imgTip1.visibility = View.GONE
            binding.imgTip1.setImageResource(tip.residImage)
            binding.lblTip1.visibility = View.GONE
        }
    }

    object TipsDiffCallback : DiffUtil.ItemCallback<Tip>(){
        override fun areItemsTheSame(oldItem: Tip, newItem: Tip): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Tip, newItem: Tip): Boolean =
            oldItem.descriptionFirst == newItem.descriptionFirst &&
                    oldItem.residImage == newItem.residImage &&
                    oldItem.title == newItem.title &&
                    oldItem.visibility == newItem.visibility &&
                    oldItem.unlockLevel == newItem.unlockLevel

    }

}

typealias OnItemClickListener = (position: Int) -> Unit