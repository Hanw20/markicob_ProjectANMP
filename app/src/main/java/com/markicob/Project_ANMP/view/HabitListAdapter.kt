package com.markicob.Project_ANMP.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.markicob.Project_ANMP.databinding.HabitListItemBinding
import com.markicob.Project_ANMP.model.Habit

class HabitListAdapter(val habitList : ArrayList<Habit>)
    :RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        holder.binding.txtHabitName.text = habitList[position].habitName
        holder.binding.txtDescription.text = habitList[position].description
        // Progress bar
        val progress = habitList[position].progress ?: 0
        val goal = habitList[position].goal ?: 1
        holder.binding.progressBar.max = goal
        holder.binding.progressBar.progress = progress
        holder.binding.txtProgress.text = "$progress / $goal ${habitList[position].unit}"

        // Status label
        val drawable = GradientDrawable()
        drawable.cornerRadius = 40f
        if (progress >= goal) {
            drawable.setColor(Color.parseColor("#4CAF50"))
            holder.binding.tvStatus.setTextColor(Color.WHITE)
            holder.binding.tvStatus.text = "Completed"
            holder.binding.imgCheckmark.visibility = android.view.View.VISIBLE
        } else {
            drawable.setColor(Color.parseColor("#E0E0E0"))
            holder.binding.tvStatus.setTextColor(Color.BLACK)
            holder.binding.tvStatus.text = "In Progress"
            holder.binding.imgCheckmark.visibility = android.view.View.GONE
        }

        holder.binding.tvStatus.background = drawable
        holder.binding.btnAdd.setOnClickListener {
            habitList[position].progress = (habitList[position].progress ?: 0) + 1
        }
        holder.binding.btnMin.setOnClickListener {
            habitList[position].progress = (habitList[position].progress ?: 0) - 1
        }

    }

    override fun getItemCount(): Int {
        return habitList.size

    }
    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }




}