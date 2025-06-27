package com.example.fit.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.fit.R
import com.example.fit.adapter.ActivityAdapter
import com.example.fit.model.ActivityItem
import com.example.fit.ui.viewmodel.ActivityViewModel
import java.text.SimpleDateFormat
import java.util.*

class WorkoutListFragment : Fragment(R.layout.fragment_activity) {
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var activityAdapter: ActivityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        Log.d("WorkoutListFragment", "Fragment created")
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_add_workout)
        val headerTextView = view.findViewById<TextView>(R.id.headerTextView)

        val isMyActivities = arguments?.getBoolean("is_my_activities", true) ?: true
        headerTextView.visibility = if (isMyActivities) View.VISIBLE else View.GONE
        Log.d("WorkoutListFragment", "isMyActivities: $isMyActivities")

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        activityAdapter = ActivityAdapter(emptyList()) { activity ->
            val intent = Intent(requireContext(), WorkoutDetailActivity::class.java).apply {
                putExtra("activity_id", activity.id)
                putExtra("activity_type", activity.type)
                putExtra("activity_distance", activity.distance)
                putExtra("activity_duration", activity.duration)
                putExtra("activity_start_time", activity.startTime)
                putExtra("activity_end_time", activity.endTime)
                putExtra("is_my_activity", activity.isMyActivity)
            }
            startActivity(intent)
        }
        
        recyclerView.adapter = activityAdapter

        activityViewModel.allActivities.observe(viewLifecycleOwner) { activities ->
            Log.d("WorkoutListFragment", "Received ${activities.size} activities from database")
            val itemsToShow = mutableListOf<ActivityItem>()
            
            if (isMyActivities) {
                val activityItems = activities.map { userActivity ->
                    ActivityItem.Activity(
                        id = userActivity.id.toString(),
                        distance = formatDistance(userActivity.distance),
                        duration = formatDuration(userActivity.duration),
                        type = userActivity.activityType.getFullName(),
                        timeAgo = formatTimeAgo(userActivity.startTime),
                        startTime = formatTime(userActivity.startTime),
                        endTime = userActivity.endTime?.let { formatTime(it) } ?: "",
                        isMyActivity = true
                    )
                }
                itemsToShow.addAll(activityItems)
                Log.d("WorkoutListFragment", "Added ${activityItems.size} my activities")
            } else {
                itemsToShow.addAll(getDemoUserActivities())
            }
            
            activityAdapter.updateItems(itemsToShow)
            Log.d("WorkoutListFragment", "Updated adapter with ${itemsToShow.size} items")
        }

        fab.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_workoutListFragment_to_newWorkoutFragment)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun formatDistance(distance: Double?): String {
        return if (distance != null) {
            if (distance >= 1000) {
                String.format("%.2f км", distance / 1000)
            } else {
                String.format("%.0f м", distance)
            }
        } else {
            "0 м"
        }
    }

    private fun formatDuration(duration: Long?): String {
        return if (duration != null) {
            val hours = duration / (1000 * 60 * 60)
            val minutes = (duration % (1000 * 60 * 60)) / (1000 * 60)
            
            when {
                hours > 0 -> String.format("%d ч %d мин", hours, minutes)
                else -> String.format("%d мин", minutes)
            }
        } else {
            "0 мин"
        }
    }

    private fun formatTimeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        return when {
            diff < 60 * 1000 -> "только что"
            diff < 60 * 60 * 1000 -> String.format("%d мин назад", diff / (60 * 1000))
            diff < 24 * 60 * 60 * 1000 -> String.format("%d ч назад", diff / (60 * 60 * 1000))
            else -> {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                dateFormat.format(Date(timestamp))
            }
        }
    }

    private fun formatTime(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }

    private fun getDemoUserActivities(): List<ActivityItem.Activity> {
        return listOf(
            ActivityItem.Activity(
                id = "example1",
                distance = "1 000 м",
                duration = "59 мин",
                type = "Велосипед 🚲",
                timeAgo = "29.05.2025",
                startTime = "10:00",
                endTime = "10:59",
                isMyActivity = false
            ),
            ActivityItem.Activity(
                id = "example2",
                distance = "2.5 км",
                duration = "32 мин",
                type = "Бег 🏃",
                timeAgo = "29.05.2024",
                startTime = "15:00",
                endTime = "15:32",
                isMyActivity = false
            )
        )
    }
}
