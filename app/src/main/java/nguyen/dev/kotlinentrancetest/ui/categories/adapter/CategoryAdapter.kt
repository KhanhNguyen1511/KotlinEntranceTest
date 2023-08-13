package nguyen.dev.kotlinentrancetest.ui.categories.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nguyen.dev.kotlinentrancetest.R
import nguyen.dev.kotlinentrancetest.repository.models.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ViewHolder>(DiffCallback()) {
    var list: List<Category> = listOf()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        tracker?.let {
            holder.bind(item, it.isSelected(position.toLong()))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textName: TextView = view.findViewById(R.id.textCategoryName)

        fun bind(value: Category, isSelected: Boolean) = with(itemView) {
            textName.text = value.name
            itemView.isSelected = isSelected
        }

        fun getItemDetail(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int {
                    return adapterPosition
                }

                override fun getSelectionKey(): Long? {
                    return itemId
                }

                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }

            }


    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
}