package com.markicob.Project_ANMP.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.markicob.Project_ANMP.R
import com.markicob.Project_ANMP.databinding.HabitListItemBinding
import com.markicob.Project_ANMP.model.Habit
import com.markicob.Project_ANMP.viewmodel.ListViewModel

class HabitListAdapter(val habitList : ArrayList<Habit>, val viewModel: ListViewModel)
    :RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]

        holder.binding.txtHabitName.text = habit.habitName
        holder.binding.txtDescription.text = habit.description
        
        // holder.binding.imgIcon.setImageResource(...)

        // Progress bar
        val progress = habit.progress ?: 0
        val goal = habit.goal ?: 1

        holder.binding.progressBar.max = goal
        holder.binding.progressBar.progress = progress
        holder.binding.txtProgress.text = "$progress / $goal ${habit.unit}"

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
            viewModel.updateProgress(position, 1)
        }
        holder.binding.btnMin.setOnClickListener {
            viewModel.updateProgress(position, -1)
        }
        val iconRes = when (habit.icon) {
            "Water" -> R.drawable.ic_water
            "Exercise" -> R.drawable.ic_fitness
            "Read" -> R.drawable.ic_book
            "Walk" -> R.drawable.ic_walk
            "Meditation" -> R.drawable.ic_meditation
            "Diet" -> R.drawable.ic_diet
            "Sleep" -> R.drawable.ic_sleep
            "Medicine" -> R.drawable.ic_medicine
            "Study" -> R.drawable.ic_study
            else -> R.drawable.ic_other
        }
        holder.binding.imgIcon.setImageResource(iconRes)

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