package com.ahmedkenawy.cfhtest.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedkenawy.cfhtest.databinding.ItemVenueBinding
import com.ahmedkenawy.cfhtest.domain.model.response.Venue

/**
 * Adapter for displaying a list of venues in a RecyclerView.
 *
 * This adapter uses a [ListAdapter] with [DiffUtil] to efficiently update the list of venues.
 * It is responsible for creating and binding view holders to display venue items.
 *
 * @property onItemClicked A lambda function to handle item clicks.
 */
class VenueAdapter(private val onItemClicked: (Venue) -> Unit) : ListAdapter<Venue, VenueAdapter.VenueViewHolder>(VenueDiffCallback()) {

    /**
     * Creates a new [VenueViewHolder] for the specified [parent] view group.
     *
     * @param parent The parent view group that will contain the created view holder.
     * @param viewType The type of view to create.
     * @return A new [VenueViewHolder] instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val binding = ItemVenueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VenueViewHolder(binding)
    }

    /**
     * Binds the venue data to the [VenueViewHolder] at the specified [position].
     *
     * @param holder The [VenueViewHolder] to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val venue = getItem(position)
        holder.bind(venue)
    }

    /**
     * ViewHolder for a single venue item.
     *
     * @property binding The binding for the venue item view.
     */
    inner class VenueViewHolder(private val binding: ItemVenueBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val venue = getItem(position)
                    onItemClicked(venue)
                }
            }
        }

        /**
         * Binds the [Venue] data to the view holder's view.
         *
         * @param venue The venue item to bind.
         */
        fun bind(venue: Venue) {
            binding.venue = venue
            binding.executePendingBindings()
        }
    }

    /**
     * DiffUtil callback for calculating differences between old and new venue lists.
     */
    class VenueDiffCallback : DiffUtil.ItemCallback<Venue>() {
        /**
         * Checks if two items represent the same [Venue].
         *
         * @param oldItem The old [Venue] item.
         * @param newItem The new [Venue] item.
         * @return True if the items are the same.
         */
        override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return oldItem.id == newItem.id
        }

        /**
         * Checks if the contents of two items are the same.
         *
         * @param oldItem The old [Venue] item.
         * @param newItem The new [Venue] item.
         * @return True if the contents of the items are the same.
         */
        override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
            return oldItem == newItem
        }
    }
}
