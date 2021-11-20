package org.nunocky.recyclerviewstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.nunocky.recyclerviewstudy.databinding.ListitemCheckboxBinding
import org.nunocky.recyclerviewstudy.databinding.ListitemTexteditBinding

private object DiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}

class MyListAdapter(private val viewLifecycleOwner: LifecycleOwner) :
    ListAdapter<Item, MyListAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, viewLifecycleOwner: LifecycleOwner) {

            when (binding) {
                is ListitemTexteditBinding -> {
                    binding.item = item as TextItem
                }
                is ListitemCheckboxBinding -> {
                    binding.item = item as BooleanItem
                }
                else -> {
                    throw IllegalArgumentException("invalid class")
                }
            }

            binding.lifecycleOwner = viewLifecycleOwner
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_TEXT -> {
                ItemViewHolder(ListitemTexteditBinding.inflate(layoutInflater, parent, false))
            }
            TYPE_CHECKBOX -> {
                ItemViewHolder(ListitemCheckboxBinding.inflate(layoutInflater, parent, false))
            }
            else -> {
                throw IllegalArgumentException("invalid class")
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TextItem -> {
                TYPE_TEXT
            }
            is BooleanItem -> {
                TYPE_CHECKBOX
            }
            else -> {
                throw IllegalArgumentException("invalid type")
            }
        }
    }

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_CHECKBOX = 1
    }
}