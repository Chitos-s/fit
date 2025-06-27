package com.example.fit.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.R
import com.example.fit.model.ActivityItem

class ActivityAdapter(
    private var items: List<ActivityItem>,
    private val onClick: (ActivityItem.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ACTIVITY = 1
    }

    fun updateItems(newItems: List<ActivityItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_ACTIVITY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item is ActivityItem.Activity) {
            (holder as ActivityViewHolder).bind(item, onClick)
        }
    }

    class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val distanceView = itemView.findViewById<TextView>(R.id.tvWorkoutDistance)
        private val durationView = itemView.findViewById<TextView>(R.id.tvWorkoutDuration)
        private val typeView = itemView.findViewById<TextView>(R.id.tvWorkoutType)
        private val timeAgoView = itemView.findViewById<TextView>(R.id.tvWorkoutTimeAgo)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: ActivityItem.Activity, onClick: (ActivityItem.Activity) -> Unit) {
            distanceView.text = item.distance
            durationView.text = item.duration
            typeView.text = item.type
            timeAgoView.text = item.timeAgo

            val textColor = if (item.isMyActivity) {
                itemView.context.getColor(R.color.activity_active)
            } else {
                itemView.context.getColor(R.color.text_secondary)
            }
            distanceView.setTextColor(textColor)
            typeView.setTextColor(textColor)
            
            itemView.setOnClickListener { onClick(item) }
        }
    }
}
