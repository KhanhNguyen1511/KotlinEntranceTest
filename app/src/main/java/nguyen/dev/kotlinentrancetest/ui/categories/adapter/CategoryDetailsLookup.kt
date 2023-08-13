package nguyen.dev.kotlinentrancetest.ui.categories.adapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class CategoryDetailsLookup(private val recyclerView: RecyclerView): ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x,e.y)
        if (view!=null) {
            return (recyclerView.getChildViewHolder(view) as CategoryAdapter.ViewHolder).getItemDetail()
        }
        return null
    }
}