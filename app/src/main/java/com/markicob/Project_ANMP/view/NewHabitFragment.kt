package com.markicob.Project_ANMP.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markicob.Project_ANMP.databinding.FragmentNewHabitBinding
import com.markicob.Project_ANMP.model.Habit
import com.markicob.Project_ANMP.viewmodel.ListViewModel
import java.util.UUID

class NewHabitFragment : Fragment() {

    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: ListViewModel

    private val iconOptions = listOf(
        "Water", "Exercise", "Read", "Walk", "Meditation", "Diet", "Sleep", "Medicine", "Study", "Other"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        // Setup spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iconOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.etHabitName.text.toString().trim()
            val desc = binding.etDescription.text.toString().trim()
            val goalStr = binding.etGoal.text.toString().trim()
            val unit = binding.etUnit.text.toString().trim()
            val goal = goalStr.toIntOrNull()
            val selectedIcon = iconOptions[binding.spinnerIcon.selectedItemPosition]

            if (name.isEmpty() || desc.isEmpty() || goalStr.isEmpty() || unit.isEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Semua field harus diisi!"
            } else if (goal == null || goal <= 0) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Goal harus berupa angka lebih dari 0!"
            } else {
                val newHabit = Habit(
                    id = UUID.randomUUID().toString(),
                    habitName = name,
                    description = desc,
                    progress = 0,
                    goal = goal,
                    unit = unit,
                    icon = selectedIcon
                )
                viewModel.addHabit(newHabit)
                findNavController().popBackStack()
            }
        }
    }
}