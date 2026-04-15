package com.markicob.Project_ANMP.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.markicob.Project_ANMP.databinding.HabitListItemBinding
import com.markicob.Project_ANMP.model.Habit

class HabitListAdapter(val habitList : ArrayList<Habit>)
    :RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {
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


    class HabitViewHolder(var binding: HabitListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}