package com.example.thelazymonday.ui.gamenews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thelazymonday.data.local.entity.GameNewsEntity
import com.example.thelazymonday.databinding.ItemGameNewsBinding

class GameNewsAdapter :
    PagedListAdapter<GameNewsEntity, GameNewsAdapter.GameNewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameNewsEntity>() {
            override fun areItemsTheSame(
                oldItem: GameNewsEntity,
                newItem: GameNewsEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GameNewsEntity,
                newItem: GameNewsEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameNewsViewHolder {
        val itemGameNewsBinding =
            ItemGameNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameNewsViewHolder(itemGameNewsBinding)
    }

    override fun onBindViewHolder(holder: GameNewsViewHolder, position: Int) {
        val gameNews = getItem(position)

        holder.bind(gameNews)
    }

    class GameNewsViewHolder(private val binding: ItemGameNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gameNews: GameNewsEntity?) {
            with(binding) {
                tvItemTitle.text = gameNews?.title
                tvTag.text = gameNews?.tag
                tvDate.text = gameNews?.time

                itemView.setOnClickListener {
//                    itemView.findNavController().navigate()
                }
            }
        }


    }
}
