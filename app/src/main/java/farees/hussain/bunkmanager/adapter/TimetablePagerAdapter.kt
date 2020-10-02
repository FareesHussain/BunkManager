package farees.hussain.bunkmanager.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TimetablePagerAdapter (val items: List<String>){
    inner class PagerViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)
}